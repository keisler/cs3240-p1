/*
 * Note: Since I do not have the code
 * necessary to fully flesh out this class,
 * I have made a few assumptions about its
 * structure, as well as the structure of
 * the classes it calls. 
 */

import java.util.HashMap;

public class Driver {
	public static void main(String[] args) {
		Parser parser = new Parser();
		NFA consolidatedNFA = parser.parse("filename.txt");
		DFA output = consolidatedNFA.toDFA();
		System.out.println(output.toString());
		output.minimize();
		System.out.println(output.toString());
	}
}

/*
 * Since I do not possess the DFA class, the following is
 * pseudocode indicating how a minimization method might
 * be implemented.
 */

public void minimize() {
	@SuppressWarnings("rawtypes")
	HashMap min = new HashMap();
	for (int i = 0; i < nodes.length - 2; i ++) {
		for (int j = i + 1; j < nodes.length - 1; j ++) {
			Node temp[] = {nodes[i], nodes[j]};
			if (nodes[i].isFinish() != nodes[j].isFinish()) {
				min.put(temp, -1);
			} else {
				min.put(temp, 0);
			}
		}
	}
	boolean fin;
	do {
		fin = true;
		for (int i = 0; i < nodes.length - 2; i ++) {
			for (int j = i + 1; j < nodes.length - 1; j ++) {
				Node temp[] = {nodes[i], nodes[j]};
				boolean brk = false;
				if (min.get(temp) == 0) {
					Node temp2[] = nodes[i].getOutput();
					Node temp3[] = nodes[j].getOutput();
					for (Node n : temp) {
						for (Node m : temp2) {
							Node temp4[] = {nodes[n], nodes[m]};
							if (min.get(temp4) == -1) {
								min.put(temp, -1);
								brk = true;
								fin = false;
							}
							if (brk) break;
						}
						if (brk) break;
					}
				}
			}
		}
	} while (!fin);
}