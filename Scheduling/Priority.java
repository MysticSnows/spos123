import java.util.Scanner;

public class Priority 
{

    int burstTime[];
    int priority[];
    int arrivalTime[];
    String[] processId;
    int numberOfProcess;

    void getProcessData(Scanner input) 
    {
        System.out.print("Enter the no. of Processes: ");
        int inputNumberOfProcess = input.nextInt();
        numberOfProcess = inputNumberOfProcess;
        burstTime = new int[numberOfProcess];
        priority = new int[numberOfProcess];
        arrivalTime = new int[numberOfProcess];
        processId = new String[numberOfProcess];
        String st = "P";
        for (int i = 0; i < numberOfProcess; i++) 
        {
            processId[i] = st.concat(Integer.toString(i));
            System.out.print("Enter the priority     for P" + (i) + " : ");
            priority[i] = input.nextInt();
            System.out.print("Enter the arrival time for P" + (i) + " : ");
            arrivalTime[i] = input.nextInt();
            System.out.print("Enter the burst time   for P" + (i) + " : ");
            burstTime[i] = input.nextInt();
            
            
        }
    }

    void sortProcesses(int[] at, int[] bt, int[] prt, String[] pid) 
    {
    	// sorting according to AT and Priority
        int temp;
        String stemp;
        for (int i = 0; i < numberOfProcess; i++) 
        {

            for (int j = 0; j < numberOfProcess - i - 1; j++) 
            {
                if (at[j] > at[j + 1]) 
                {
                    //swapping arrival time
                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    //swapping burst time
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    //swapping priority
                    temp = prt[j];
                    prt[j] = prt[j + 1];
                    prt[j + 1] = temp;

                    //swapping process identity
                    stemp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = stemp;

                }
                //sorting according to priority when arrival timings are same
                if (at[j] == at[j + 1]) 
                {
                    if (prt[j] > prt[j + 1]) 
                    {
                        //swapping arrival time
                        temp = at[j];
                        at[j] = at[j + 1];
                        at[j + 1] = temp;

                        //swapping burst time
                        temp = bt[j];
                        bt[j] = bt[j + 1];
                        bt[j + 1] = temp;

                        //swapping priority
                        temp = prt[j];
                        prt[j] = prt[j + 1];
                        prt[j + 1] = temp;

                        //swapping process identity
                        stemp = pid[j];
                        pid[j] = pid[j + 1];
                        pid[j + 1] = stemp;

                    }
                }
            }

        }
    }

    void priorityNonPreemptiveAlgorithm() 
    {
        int finishTime[] = new int[numberOfProcess];
        int bt[] = burstTime.clone();
        int at[] = arrivalTime.clone();
        int prt[] = priority.clone();
        String pid[] = processId.clone();
        int waitingTime[] = new int[numberOfProcess];
        int turnAroundTime[] = new int[numberOfProcess];

        sortProcesses(at, bt, prt, pid);

        //calculating waiting & turn-around time for each process
        finishTime[0] = at[0] + bt[0];
        turnAroundTime[0] = finishTime[0] - at[0];
        waitingTime[0] = turnAroundTime[0] - bt[0];

        for (int i = 1; i < numberOfProcess; i++) 
        {
            finishTime[i] = bt[i] + finishTime[i - 1];
            turnAroundTime[i] = finishTime[i] - at[i];
            waitingTime[i] = turnAroundTime[i] - bt[i];
        }
        float sum = 0;
        for (int n : waitingTime) 
        {
            sum += n;
        }
        float avgWT = sum / numberOfProcess;

        sum = 0;
        for (int n : turnAroundTime) 
        {
            sum += n;
        }
        float avgTAT = sum / numberOfProcess;

        //print on console the order of processes along with their finish time & turn around time
        System.out.print("Priority Scheduling Algorithm : ");
        System.out.print("\nProcessId \t BurstTime \t ArrivalTime \t Priority \t FinishTime \t WaitingTime \t TurnAroundTime");
        for (int i = 0; i < numberOfProcess; i++) {
            System.out.print("\n" + pid[i] + "\t\t" + bt[i] + "\t\t" + at[i] + "\t\t" + prt[i] + "\t\t" + finishTime[i] + "\t\t" + waitingTime[i] + "\t\t"+ turnAroundTime[i]);
        }

        System.out.println("Average WT = " + avgWT + "Average TAT= " + avgTAT);
    }

    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        Priority obj = new Priority();
        obj.getProcessData(input);
        obj.priorityNonPreemptiveAlgorithm();
    }
}
 

