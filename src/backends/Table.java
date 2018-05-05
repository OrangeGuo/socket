package backends;

public class Table {
    int[] ports;
    public Table(){
        this.ports=new int[4];
        for (int i = 0; i < 4; i++) {
            this.ports[i]=-1;
        }
    }
    public boolean queryTable(int i){
        if(ports[i]!=i) {
            ports[i]=i;
            this.display();
            return false;
        }


        return true;
    }
    public void display(){
        for (int i = 0; i < 4; i++) {
            System.out.print("端口"+(i+1)+" ");

        }
        System.out.println();
        for (int i = 0; i < 4; i++) {
            if(ports[i]==-1)
                System.out.printf("空闲中 ");
            else
                System.out.printf("主机"+(i+1)+" ");
        }
        System.out.println();
    }
}
