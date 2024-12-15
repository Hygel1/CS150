 
/**
 * All helper methods have been left private, all methods required for a single-cycle step have been made puvlic to allow accessor class to choose between manual control and auto-run
 */
public class River {
    private Animal[] river;
    private int cycleTracker, fishTracker, bearTracker, femaleTracker, maleTracker;
    private float initPerc;
    /**
     * builds river array and sets inital population, each index is randomly assigned 1 of 3 values (new fish, new bear, or null)
     * @param int value designates river size
     */
    public River(int size){
        river = new Animal[size];
        cycleTracker=0;fishTracker=0;femaleTracker=0;maleTracker=0;
        for(int i=0;i<river.length;i++){ //run through every index and decide initial position
            int hold=(int)(Math.random()*3); //random 0-2
            if(hold==0){
                river[i]=new Bear();
                bearTracker++; //increment bear tracker to track bears
            }
            else if(hold==1){
                river[i]=new Fish();
                fishTracker++; //increment fish tracker to track fish 
            }
            //in case of value 2, river spot is left null
            if(hold<2){
                if(river[i].checkGender()==Animal.Gender.MALE) maleTracker++; //increment male tracker to track males
                else femaleTracker++; //increment female tracker to count females
            }
            
        }
        
        initPerc=(float)fishTracker/(fishTracker+bearTracker); //process initial count values

    }
    /**
     * runs river cycling until river is found to be fully empty (advance and print state), then prints full stat brief (male/female ratio, bear/fish ratio)
     */
    public void autoRun(){
        printRiver(); //update console (initial)
        while(!gameOver()){ //while there exits non-null indices
            advanceYear(); //advance life cycle once
            printRiver(); //update console
        }
        /**
        //print stat briefing - old stat printing system, would be useful in other implementation
        System.out.println("Male: "+maleTracker+" "+(100*(double)maleTracker/(maleTracker+femaleTracker))+"%");
        System.out.println("Female: "+femaleTracker+" "+(100*(double)femaleTracker/(maleTracker+femaleTracker))+"%");
        System.out.println("Bears: "+bearTracker+" "+(100*(double)bearTracker/(bearTracker+fishTracker))+"%");
        System.out.println("Fish: "+fishTracker+" "+(100*(double)fishTracker/(bearTracker+fishTracker))+"%");
        */
    }
    /**
     * ages all animals one year and runs a single cycle of potential movement for all animals in array
     */
    public void advanceYear(){
        cycleTracker++;
        for(int i=0;i<river.length;i++){ //run through array to perform initial aging
            if(river[i]!=null){
                river[i].ageOneYear();
                if(river[i].checkType()==1&&river[i].checkAge()>5) //kill off over-aged animals
                    river[i]=null;
                else if(river[i].checkType()==0&&river[i].checkAge()>9)
                    river[i]=null;
            }
        }
        //start movement process
        for(int i=0;i<river.length;i++){
            if(river[i]!=null){
                int movement=(int)(Math.random()*3);//0=leftward movement, 1=no movement, 2=rightward movement (is wrapping allowed??)
                if(movement==0&&i>0) approaching(i,i-1); //index i attempts to move leftward
                else if(movement==0) approaching(i,river.length-1); //index i attemps to wrap river leftward
                else if(movement==2&&i<river.length-1) approaching(i,i+1); //index i attempts to move rightward
                else if(movement==2) approaching(i, 0); //index i attempts to wrap river rightward
            }
        }
    }
    /**
     * handles interactions between animals during the movement process, allows for fights, stalemates, and breeding
     * 
     * @param approaching index of moving animal
     * @param i2 index of animal being approached
     */
    private void approaching(int approaching,int i2){
        if(river[i2]==null){ //if cell to move into is empty, animal can move without interaction
            river[i2]=river[approaching];
            river[approaching]=null;
        }
        else if(river[approaching].checkType()==river[i2].checkType()&&river[approaching].checkGender()!=river[i2].checkGender()){ //similar type, different gender, mating process
            mate(river[approaching].checkType());
        }
        else if(river[approaching].checkType()>river[i2].checkType()){ //fish is approaching bear, fish dies
            river[approaching]=null;
        }
        else if(river[approaching].checkType()<river[i2].checkType()){ //bear is approaching fish, bear moves and fish dies
            river[i2]=river[approaching];
            river[approaching]=null;
        }
        else if(river[approaching].checkType()==0&&river[i2].checkType()==0){ //bear approaching bear, lower strength dies (gender dispute handled in second if statement)
            if(((Bear)river[approaching]).checkStrength()>((Bear)river[i2]).checkStrength()){ //if approaching bear is stronger than i2, approaching moves and i2 dies
                river[i2]=river[approaching];
                river[approaching]=null;
            }
            else if(((Bear)river[approaching]).checkStrength()<((Bear)river[i2]).checkStrength()){ //if approaching is weaker than i2, approaching dies and i2 stays
                river[approaching]=null;
            }
            //if same strength, nothing happens
        }

    }
    /**
     * searches for potential birthing placements for a new animal, then fills a null index with a new animal cooresponding to the passed type
     * 
     * @param type represents the type of animal to be birthed, 0=bear, 1=fish
     */
    private void mate(int type){
        boolean full=true;
        for(int k=0;k<river.length;k++){ //check if array is full
            if(river[k]==null){ 
                full=false;
                break;
            }
        }
        if(!full){
            int randInd=-1; //placeholder index while searching for random null value
            while(randInd<0){ //continuously randomly select indices until a null value is found, there exists at least one as was found in previous block
                int holdInd=(int)(Math.random()*(river.length));
                if(river[holdInd]==null) randInd=holdInd;
            }
            if(type==0){
                river[randInd]=new Bear();
                bearTracker++; //increment tracker to track bears
            }
            else{
                river[randInd]=new Fish();
                fishTracker++; //increment tracker to count fish
            }
            if(river[randInd].checkGender()==Animal.Gender.MALE) maleTracker++; //increment tracker to find males
            else femaleTracker++; //increment tracker to find females
        }
    }
    /**
     * Prints full explanation of the current state of the river array,
     * represents each animal as its toString() value and states the number of completed cycles
     */
    public void printRiver(){
        for(int i=0;i<river.length;i++){
            System.out.print((river[i]==null?"---":river[i])+" ");
            }
        System.out.println(" | Cycle: "+cycleTracker);
    }
    /**
     * checks to see if all indices in the river are null (eg: there is no possible next move to be made)
     * @return boolean to represent whether or not the simulation is fully exhausted
     */
    public boolean gameOver(){
        for(int i=0;i<river.length;i++){
            if(river[i]!=null) return false;
        }
        return true;
    }
    /**
     * accessor method for all stat values collected over runtime
     * @return integer array representing femaleTracker, maleTracker, fishTracker, bearTracker
     */
    public int[] stats(){
        int[] rtn = new int[4];
        rtn[0]=femaleTracker;
        rtn[1]=maleTracker;
        rtn[2]=fishTracker;
        rtn[3]=bearTracker;
        return rtn;
    }
    /**
     * returns float value representing initial birthing spread across river
     * @return float value representing the percentage of initial values which were birthed as fish
     */
    public float initDiff(){
        return initPerc;
    }
}
