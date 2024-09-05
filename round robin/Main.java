import java.util.*;

public class Main
{
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        
        List<Process> processes = new ArrayList<>();
        Queue<Process> saf = new LinkedList<>();
        double totalTurnAroundTime = 0;
        double totalWaitingTime = 0;
        double avgTurnAroundTime = 0;
        double avgWaitingTime = 0;
        int min = Integer.MAX_VALUE;

        System.out.print("enter number of processes: ");
        int n = scan.nextInt();
        System.out.println("enter processes name-burst-enteringTime (with given format): ");
        for (int i = 0; i < n; i++) {
            String[] arr = scan.next().split("-");
            String pName = arr[0];
            int burst = Integer.valueOf(arr[1]);
            int enteringTime = Integer.valueOf(arr[2]);
            min = enteringTime < min ? enteringTime : min;
            Process p = new Process(pName, burst, enteringTime);
            processes.add(p);;
        }
        System.out.print("enter time quantum: ");
        int timeQuantum = scan.nextInt();
        scan.close();
        System.out.println("----------------------------");

        processes.sort(Comparator.comparing(Process::getEnteringTime));

        int i = min;
        boolean firstP = true;
        while (true) {
            for (int j = 0; j < processes.size() && firstP; j++) {
                if (processes.get(j).getEnteringTime() == min) {
                    saf.add(processes.get(j));
                    processes.remove(j);
                    j--;
                }
            }
            firstP = false;
            
            Process currentProcess = saf.poll();
            if (currentProcess == null) {
                if (processes.isEmpty()) {
                    break;
                }
                continue;
            }
            if (currentProcess.getBurst() > timeQuantum) {
                currentProcess.setBurst(currentProcess.getBurst() - timeQuantum);
                currentProcess.setWaitingTime(currentProcess.getWaitingTime() + (i - currentProcess.getLastTurnTime()));
                totalWaitingTime += i - currentProcess.getLastTurnTime();
                i += timeQuantum;
                currentProcess.setLastTurnTime(i);
                for (int j = 0; j < processes.size(); j++) {
                    if (processes.get(j).getEnteringTime() <= i) {
                        saf.add(processes.get(j));
                        processes.remove(j);
                        j--;
                    }
                }
                saf.add(currentProcess);
            } else {
                currentProcess.setWaitingTime(currentProcess.getWaitingTime() + (i - currentProcess.getLastTurnTime()));
                totalWaitingTime += i - currentProcess.getLastTurnTime();
                i += currentProcess.getBurst();
                currentProcess.setTurnAroundTime(i - currentProcess.getEnteringTime());
                totalTurnAroundTime += currentProcess.getTurnAroundTime();
                System.out.println(currentProcess.getName() + " finished at time " + i +
                " with turn aroud time = " + currentProcess.getTurnAroundTime() + 
                " and waiting time = " + currentProcess.getWaitingTime());
                for (int j = 0; j < processes.size(); j++) {
                    if (processes.get(j).getEnteringTime() <= i) {
                        saf.add(processes.get(j));
                        processes.remove(j);
                        j--;
                    }
                }
            }
        }
        avgTurnAroundTime = totalTurnAroundTime / n;
        avgWaitingTime = totalWaitingTime / n;
        System.out.println("total turn aroud time: " + totalTurnAroundTime + " | average turn aroud time: " + String.format("%.2f", avgTurnAroundTime));
        System.out.println("total waiting time: " + totalWaitingTime + " | average waiting time: " + String.format("%.2f", avgWaitingTime));
        System.out.println("----------------------------");
    }
}