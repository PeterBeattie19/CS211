public class MinHeap {

  private Node heapArray[];
  private int maxSize;
  private int currentSize;

  public MinHeap(int max){
    this.maxSize = max;
    this.currentSize = 0;
    heapArray = new Node[maxSize];
  }

  public boolean isEmpty(){
    return (currentSize==0);
  }

  //Add new Node to Heap
  public boolean insert(int n, char c){
    if(currentSize==maxSize) return false;

    Node newNode = new Node(n,c);
    heapArray[currentSize] = newNode;
    trickleUp(currentSize++);
    return true;
  }

  public void trickleUp(int index){
    int parent = (index-1)/2; //Parent of newly inserted node
    Node bottom = heapArray[index]; //New inserted Node

    while(index>0 && heapArray[parent].getKey() > bottom.getKey()){ //Loop while we're still in the array and the current node's key is greater than its parent
      heapArray[index] = heapArray[parent]; //Move parent to the index
      index = parent; //change index to previous parent's index
      parent = (parent-1)/2; //change parent, move it up the heap
    }
    heapArray[index] = bottom; //Loop ends and we have found the new location fir the node
  }

  //Pop Node, to be called in main
  public Node remove(){
    Node root = heapArray[0];
    heapArray[0] = heapArray[--currentSize];
    trickleDown(0);
    return root;
  }

  public void trickleDown(int index){
    int smallerChild;
    Node top = heapArray[index];
    while(index<currentSize/2){
      int leftChild = 2*index+1;
      int rightChild = leftChild+1;

      if(rightChild < currentSize && heapArray[leftChild].getKey()<heapArray[rightChild].getKey()){
        smallerChild = leftChild;
      }
      else smallerChild = rightChild;

      if(top.getKey()<heapArray[smallerChild].getKey()) break;

      heapArray[index] = heapArray[smallerChild];
      index = smallerChild;
    }
    heapArray[index] = top;
  }

  //Peek don't remove 
  public Node peek(){
    if(currentSize!=0)
    return heapArray[0];

    else return null;
  }

  //Aleter a specific nodes data value
  public boolean change(int index, int newValue){
    if(index<0 || index>=currentSize) return false;
    int oldValue = heapArray[index].getKey();

    if(oldValue<newValue)
      trickleUp(index);
    else
      trickleDown(index);
    return true;
  }


  public int size(){
    return this.currentSize;
  }

  //to be called within the class 
  public void remove(int key){
    int index = 0;

    while(index < currentSize){
      if(heapArray[index].getKey() == key) break;
      index++;
    }

    if(index == currentSize) return;

    heapArray[index] = heapArray[--currentSize];
    trickleDown(index);
  }

}
