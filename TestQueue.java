public class TestQueue{
    public static void main(String[] args){ 
        System.out.println("Testing Queue"); 
	System.out.println("Make sure that your size amount is set from 1000 to 10 (Once testing is past set it back to 1000)");
        Queue j = new Queue();
	j.enqueue(new QNode(0, "WOW"));
	System.out.println(j.dequeue().getWord());
	j.enqueue(new QNode(1, "FIRST"));
	j.enqueue(new QNode(1, "SECOND"));
	j.enqueue(new QNode(1, "THIRD"));
	j.enqueue(new QNode(1, "FORTH"));
	j.enqueue(new QNode(1, "FIFTH"));
	j.enqueue(new QNode(1, "SIXTH"));
	j.enqueue(new QNode(1, "SEVENTH"));
	j.enqueue(new QNode(1, "EIGHTH"));
	j.enqueue(new QNode(1, "NINETH"));
	j.enqueue(new QNode(1, "TENTH"));
	j.enqueue(new QNode(1, "ELEVENTH"));
	j.enqueue(new QNode(1, "TWELVETH"));
	System.out.println("1 - 12  have been placed");
	j.print();
	System.out.println(j.dequeue().getWord());
	System.out.println(j.dequeue().getWord());
	System.out.println(j.dequeue().getWord());
	System.out.println(j.dequeue().getWord());
	System.out.println(j.dequeue().getWord());
	System.out.println(j.dequeue().getWord());
	System.out.println(j.dequeue().getWord());
	System.out.println(j.dequeue().getWord());
	System.out.println(j.dequeue().getWord());
	System.out.println(j.dequeue().getWord());
	System.out.println(j.dequeue().getWord());
	System.out.println(j.dequeue().getWord());
	System.out.println("TEST COMPLETE"); 
    }
}
        
