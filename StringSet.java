/**
 * This is a string set data structure, that is implemented as a Hash Table. 
 * This data structure supports operations insert, find and print - that insert a new 
 * String, finds a String key and prints the contents of the data structure resp.
 */
public class StringSet {

  StringNode [] table;	// Hash table - collisions resolved through chaining.
  int numelements;	// Number of elements actually stored in the structure.
  int size;					// Allocated memory (size of the hash table).

  /** 
   * Constructur: initilaizes numelements, size and initial table size.
   */
  public StringSet() {
    numelements = 0;
    size = 100;
    table = new StringNode[size];
  }

  /*
   * inserts a new key into the set. Inserts it at the head of the linked list given by its hash value.
   */
  public void insert(String key) {
    int index = hash(key);
    if (numelements == size) {
      //need to expand the hash table and rehash its contents. 

      StringNode [] newTable = new StringNode[size * 2];
     // System.out.println(index);
      for(int i = 0; i < size; i++)
      {
        StringNode cur = table[i];
        while(cur != null)
        {
          String tempS = cur.getKey();
          int tempHash = hash(tempS);
          StringNode newNode = new StringNode(tempS, newTable[tempHash]);
          newTable[tempHash] = newNode;
          cur = cur.getNext();
        }
      }
      this.table = newTable;
    }
    //Code for actual insert.
    StringNode newHead = new StringNode(key, table[index]);
    table[index] = newHead;
    numelements++;
  }

  /*
   * finds if a String key is present in the data structure. Returns true if found, else false.
   */
  public boolean find(String key) {
    int i = hash(key);
    for (StringNode cur = table[i]; cur != null; cur = cur.getNext())
     {
      if (cur.getKey().equals(key)) 
      {
        return true;
      }
    }
    return false;
  }

  /*
   * Prints the contents of the hash table.
   */
  public void print() {
    // TODO: Print the contents of the hash table.
    for (int i = 0; i < size; i++) {
			for (StringNode curr = table[i]; curr != null; curr = curr.getNext())
        System.out.println(curr.getKey());
    }
  }

  /*
   * The hash function that returns the index into the hash table for a string k.
   */
  private int hash(String k) {
    // TODO: Compute a polynomial hash function for the String k. Make sure that the hash value h returned is a proper index 
    // in the hash table, ie. in the range [0...capacity-1]. 
    int c = 17;
    int h = 0;
    for (int i = 0; i < k.length(); i++)
    {
    h = ((h * c + k.charAt(i)) % size);
    }
    return h;
  }
}
