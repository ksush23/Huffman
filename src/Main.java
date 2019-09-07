import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        String _line = line;

        ArrayList<ArrayList> arrayLists = new ArrayList<>();

        while (line.length() != 0) {
            Character c = line.charAt(0);
            String s = c + "";
            Node node = new Node(s, null, null);

            int count = countChar(line, c);

            ArrayList arrayList = new ArrayList();
            arrayList.add(node);
            arrayList.add(count);

            arrayLists.add(arrayList);

            line = line.replaceAll(s, "");
        }

        int number = arrayLists.size();
        int codeNumber = 0;

        String s = "";
        if (arrayLists.size() == 1){
            s = "0";
            codeNumber = (Integer)arrayLists.get(0).get(1);
        }

        while (arrayLists.size() != 1) {

            int min = 10000;
            int index = 0;
            Node node = null;
            String minValue = "";

            for (int i = 0; i < arrayLists.size(); i++) {
                int value = (int) arrayLists.get(i).get(1);
                if (value < min) {
                    min = value;
                    index = i;
                    node = (Node) arrayLists.get(i).get(0);
                    minValue = node.getValue();
                }
            }

            arrayLists.remove(index);

            int secondMin = 10000;
            String secondMinValue = "";
            Node secondNode = null;

            for (int i = 0; i < arrayLists.size(); i++) {
                int value = (int) arrayLists.get(i).get(1);
                if (value < secondMin) {
                    secondMin = value;
                    index = i;
                    secondNode = (Node) arrayLists.get(i).get(0);
                    secondMinValue = secondNode.getValue();
                }
            }

            arrayLists.remove(index);

            Node newNode = new Node(minValue + secondMinValue, node, secondNode);

            ArrayList arrayList = new ArrayList();
            arrayList.add(newNode);
            arrayList.add(min + secondMin);

            arrayLists.add(arrayList);
            codeNumber += min + secondMin;
        }

        System.out.println(number + " " + codeNumber);

        HashMap<String, String> hashMap = new HashMap<>();
        printCode((Node)arrayLists.get(0).get(0), s, hashMap);

        for (int i = 0; i < _line.length(); i++){
            String subLine = _line.charAt(i) + "";
            System.out.print(hashMap.get(subLine));
        }
    }

    public static void printCode(Node root, String s, HashMap<String, String> hashMap)
    {
        if (root.getLeftChild()
                == null
                && root.getRightChild()
                == null) {

            System.out.println(root.getValue() + ": " + s);
            hashMap.put(root.getValue(), s);

            return;
        }
        printCode(root.getLeftChild(), s + "0", hashMap);
        printCode(root.getRightChild(), s + "1", hashMap);
    }


    public static int countChar(String str, char c)
    {
        int count = 0;

        for(int i=0; i < str.length(); i++)
        {    if(str.charAt(i) == c)
            count++;
        }

        return count;
    }
}

class Node{

    private String value;
    private Node leftChild;
    private Node rightChild;

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node (String value, Node leftChild, Node rightChild){
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
