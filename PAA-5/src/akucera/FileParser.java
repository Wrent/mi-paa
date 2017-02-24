package akucera;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Parses files with SAT in DIMACS format.
 */
public class FileParser {
    /**
     * Parses the SATConfiguration from the input reader.
     * @param br Buffered reader
     * @return SAT Configuration object
     * @throws IOException
     */
    public static SATConfiguration parseConfFile(BufferedReader br) throws IOException {
        String line = null;
        SATFormula formula = null;
        //read lines
        while ((line = br.readLine()) != null) {
            //comment line
            if (line.toCharArray()[0] == 'c') {
                continue;
            }

            if (line.toCharArray()[0] == '0' || line.toCharArray()[0] == '%') {
                break;
            }

            //configuration line
            if (line.toCharArray()[0] == 'p') {
                formula = readConfLine(line);
                continue;
            }
            //clauses lines
            else {
                readClauseLine(line, formula);
            }
        }
        //init the configuration with formula
        SATConfiguration conf = new SATConfiguration(formula);

        return conf;
    }

    /**
     * Reads the clause from the line and adds it to the formula.
     * @param line line to be read
     * @param formula SAT formula
     */
    private static void readClauseLine(String line, SATFormula formula) {
        Scanner scanner = new Scanner(line);
        SATClause clause = new SATClause(formula.getN());

        while (scanner.hasNextInt()) {
            int next = scanner.nextInt();
            //zero terminates the reading
            if (next == 0) {
                break;
            }

            //is variable as negation?
            int val = next > 0 ? 1 : -1;

            clause.setVarInClause(Math.abs(next), val);
        }

        formula.addClause(clause);
    }

    /**
     * Reads the configuration line and returns the initilized formula.
     * @param line configuration line
     * @return Initilized formula
     */
    private static SATFormula readConfLine(String line) {
        Scanner scanner = new Scanner(line);

        //first two blocks of chars are 'p' and 'cnf'
        scanner.next();
        scanner.next();

        int n = scanner.nextInt();
        int clausesCount = scanner.nextInt();

        SATFormula formula = new SATFormula(n, clausesCount);
        return formula;
    }
}
