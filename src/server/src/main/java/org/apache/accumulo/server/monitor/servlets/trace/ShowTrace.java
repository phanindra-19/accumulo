package org.apache.accumulo.server.monitor.servlets.trace;

import static java.lang.Math.min;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.accumulo.core.client.Scanner;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Range;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.trace.SpanTree;
import org.apache.accumulo.core.trace.SpanTreeVisitor;
import org.apache.accumulo.core.trace.TraceDump;
import org.apache.accumulo.core.trace.TraceFormatter;
import org.apache.accumulo.server.monitor.servlets.BasicServlet;
import org.apache.hadoop.io.Text;

import cloudtrace.thrift.RemoteSpan;

public class ShowTrace extends Basic {

    private static final long serialVersionUID = 1L;
    
    String getTraceId(HttpServletRequest req) {
        return getStringParameter(req, "id", null);
    }

    @Override
    public String getTitle(HttpServletRequest req) {
        String id = getTraceId(req);
        if (id == null)
            return "No trace id specified";
        return "Trace ID " + id;
    }

    @Override
    public void pageBody(HttpServletRequest req, HttpServletResponse resp, final StringBuilder sb)
            throws Exception {
        String id = getTraceId(req);
        if (id == null) {
            return;
        }
        Scanner scanner = getScanner(sb);
        if (scanner == null) {
            return;
        }
        Range range = new Range(new Text(id));
        scanner.setRange(range);
        SpanTree tree = new SpanTree();
        long start = Long.MAX_VALUE;
        for (Entry<Key, Value> entry : scanner) {
            RemoteSpan span = TraceFormatter.getRemoteSpan(entry);
            tree.addNode(span);
            start = min(start, span.start);
        }
        sb.append("<style>\n");
        sb.append(" td.right { text-align: right }\n");
        sb.append(" table.indent { position: relative; left: 10% }\n");
        sb.append(" td.left { text-align: left }\n");
        sb.append("</style>\n");
        sb.append("<script language='javascript'>\n");
        sb.append("function toggle(id) {\n");
        sb.append(" var elt = document.getElementById(id);\n");
        sb.append(" if (elt.style.display=='none') {\n");
        sb.append("    elt.style.display='table-row';\n");
        sb.append(" } else { \n");
        sb.append("    elt.style.display='none';\n ");
        sb.append(" }\n");
        sb.append("}\n");
        sb.append("</script>\n");
        sb.append("<div>");
        sb.append("<table><caption>");
        sb.append(String.format("<span class='table-caption'>Trace started at<br>%s</span></caption>", id, dateString(start)));
        sb.append("<tr><th>Time</th><th>Start</th><th>Service@Location</th><th>Name</th><th>Addl Data</th></tr>");

        final long finalStart = start;
        Set<Long> visited = tree.visit(new SpanTreeVisitor() {
            @Override
            public void visit(int level, RemoteSpan parent, RemoteSpan node, Collection<RemoteSpan> children) {
                sb.append("<tr>\n");
                sb.append(String.format("<td class='right'>%d+</td><td class='left'>%d</td>\n", node.stop - node.start, node.start - finalStart));
                sb.append(String.format("<td style='text-indent: %dpx'>%s@%s</td>\n", level*5, node.svc, node.sender));
                sb.append("<td>" + node.description + "</td>");
                boolean hasData = node.data != null && !node.data.isEmpty();
                if (hasData)
                    sb.append("<td><input type='checkbox' onclick='toggle(\"" + Long.toHexString(node.spanId) + "\")'></td>\n");
                else
                    sb.append("<td></td>\n");
                sb.append("</tr>\n");
                sb.append("<tr id='" + Long.toHexString(node.spanId) + "' style='display:none'>");
                sb.append("<td colspan='5'>\n");
                sb.append("  <table class='indent,noborder'>\n");
                for (Entry<String, String> entry : node.data.entrySet()) {
                    sb.append("  <tr><td>" + BasicServlet.sanitize(entry.getKey()) + "</td>");
                    sb.append("<td>" + BasicServlet.sanitize(entry.getValue()) + "</td></tr>\n");
                }
                sb.append("  </table>");
                sb.append("</td>\n");
                sb.append("</tr>\n");
            }
        });
        tree.nodes.keySet().removeAll(visited);
        if (!tree.nodes.isEmpty()) {
            sb.append("<span type='warn'>Warning: the following spans are not rooted!</span>\n");
            sb.append("<ul>\n");
            for (RemoteSpan span : TraceDump.sortByStart(tree.nodes.values())) {
                sb.append(String.format("<li>%s %s %s</li>\n", Long.toHexString(span.spanId), Long.toHexString(span.parentId), span.description));
            }
            sb.append("</ul>\n");
        }
        sb.append("</table>\n");
        sb.append("</div>\n");
    }
}
