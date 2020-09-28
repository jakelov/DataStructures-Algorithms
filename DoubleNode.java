public class DoubleNode {
    double x;
    double y;
    DoubleNode next;

    public DoubleNode(double _x, double _y) {
        x = _x;
        y = _y;
        next = null;
    }

    public DoubleNode(double _x, double _y, DoubleNode _next) {
        x = _x;
        y = _y;
        next = _next;
    }
}
