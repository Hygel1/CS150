package Project1;
import java.util.Scanner;
/*
 * 
 */
public class RiverSimulator {
    public static void main(String[] args){
        int[] riverParam = setup(); //take user input for river parameters
        if(riverParam[0]==1){
            River river = new River(riverParam[1]);
            for(int i=0;i<riverParam[2];i++){
                river.advanceYear();
                river.printRiver();
                if(river.gameOver()){
                    System.out.println("River fully died before designated number of cycles");
                    break;}
            }
            if(riverParam[2]==-1) river.autoRun(); //run until end if parameters permit
            int[] stat = river.stats(); //pull stats from runtime
            //print stat briefing
        System.out.println("Male: "+stat[1]+", "+(100*(double)stat[1]/(stat[0]+stat[1]))+"%");
        System.out.println("Female: "+stat[0]+", "+(100*(double)stat[0]/(stat[0]+stat[1]))+"%");
        System.out.println("Bears: "+stat[3]+", "+(100*(double)stat[3]/(stat[3]+stat[2]))+"%");
        System.out.println("Fish: "+stat[2]+", "+(100*(double)stat[2]/(stat[3]+stat[2]))+"%");
        System.out.println("Initial Pop: "+river.initDiff()*100+"% fish, "+(1-river.initDiff())*100+"% bear");
        }
        else System.out.println("Closing Simulation.");

    }
    /**
     * takes user input to determine paramters of simulation (run/exit, size, runtime)
     * @return
     */
    private static int[] setup(){
        Scanner scn=new Scanner(System.in);
        int[] rtn = new int[3];
        while(rtn[0]>2||rtn[0]<1){
            System.out.println("River Ecosystem Simulator: \nPlease Choose: 1 (Random River) 2 (Exit)");
            try{
                rtn[0]=Integer.parseInt(scn.nextLine()); //parseInt to avoid endless loop if given a non-number character
            }
            catch(Exception e){rtn[0]=-1;}
        }
        if(rtn[0]==2){ //in case of 'exit' input, second/third index doesn't matter
             rtn[1]=0;
            rtn[2]=0;
        }
        else{
            rtn[1]=-1;
            while(rtn[1]<1){
                System.out.println("Enter River Size (positive integer):");
                try{
                    rtn[1]=Integer.parseInt(scn.nextLine()); //parseInt to avoid endless loop if given a non-number character
                }
                catch(Exception e){rtn[1]=-1;}
            }
            rtn[2]=-1;
            while(rtn[2]<1){
                String res;
                System.out.println("Enter number of cycles to consider (positive integer): ");
                res=scn.next();
                try{
                    rtn[2]=Integer.parseInt(res); //parseInt to allow for 'all' input
                }catch(NumberFormatException e){
                    if(res.equals("all")) break; //type 'all' to consider all cycles until all spaces are null
                }
            }
        }
        scn.close();
        return rtn;
    }
}
