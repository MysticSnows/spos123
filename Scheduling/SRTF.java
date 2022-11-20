import java.util.*;

// Preemptive SJF (Shortest Remaining Time First)
public class SRTF {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter no of process:");
		int n = sc.nextInt();
		int pid[] = new int[n];
		int at[] = new int[n];
		int bt[] = new int[n];
		int ct[] = new int[n];
		int ta[] = new int[n];
		int wt[] = new int[n];
		int f[] = new int[n]; // to checks if process is completed or not
		int k[] = new int[n]; // to store original Burst Times
		int i, st = 0, tot = 0;
		float avgwt = 0, avgta = 0;

		for (i = 0; i < n; i++) {
			pid[i] = i + 1;
			System.out.println("enter process " + (i + 1) + " arrival time:");
			at[i] = sc.nextInt();
			System.out.println("enter process " + (i + 1) + " burst time:");
			bt[i] = sc.nextInt();
			k[i] = bt[i];
			f[i] = 0;
		}

		while (true) {
			int min = 99, c = n;
			if (tot == n) // total no of process = completed process: terminate
				break;

			for (i = 0; i < n; i++) {
				/*
				 * If i'th process arrival time <= system time && flag=0 && burst<min 
				 * That process will be executed first
				 */
				if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
					min = bt[i];
					c = i;
				}
			}
			/*
			 * If c==n means c value can not updated because 
			 * no process arrival time < system time so, increment system time
			 */
			if (c == n)
				st++;
			// else block is different in SRTF than in SJF
			else {
				bt[c]--;
				st++;
				if (bt[c] == 0) {
					ct[c] = st;
					f[c] = 1;
					tot++;
				}
			}
		}

		for (i = 0; i < n; i++) {
			ta[i] = ct[i] - at[i];
			wt[i] = ta[i] - k[i];
			avgwt += wt[i];
			avgta += ta[i];
		}

		System.out.println("\npid \tAT \tBT \tCT \tTAT \tWT");
		System.out.println("--------------------------------------------");
		for (i = 0; i < n; i++) {
			System.out.println(pid[i] + "\t" + at[i] + "\t" + k[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
		}

		System.out.println("\naverage tat is " + (float) (avgta / n));
		System.out.println("average wt is " + (float) (avgwt / n));
		sc.close();
	}
}