package a2_2101140030;

import utils.DomainConstraint;
import utils.NotPossibleException;

import java.util.Scanner;
import java.util.Vector;
import static utils.TextIO.*;

/**
 * @overview PCProg is a program that captures data about PC objects
 *           and displays a report about them on the console.
 * 
 * @attributes
 *             objs Set<PC>
 * 
 * @object
 *         A typical PCProg is {c1,...,cn} where c1,...,cn are pcs
 * 
 * @abstract_properties
 *                      mutable(objs)=true /\ optional(objs)=false
 *
 */
public class PCProg {
  @DomainConstraint(mutable = true, optional = false)
  private Set<PC> objs;

  /**
   * @effects
   *          initialise this to have an empty set of PCs
   */
  public PCProg() {
    objs = new Set<>();
  }

  /**
   * @effects
   *          if objs is not empty
   *          display to the standard console a text-based tabular report on objs
   *          return this report
   *          else
   *          display nothing and return null
   */
  public String displayReport() {
    if (objs.size() > 0) {
      Vector<PC> pcs = objs.getElements();

      PCReport reportObj = new PCReport();
      return reportObj.displayReport(pcs.toArray(new PC[pcs.size()]));
    } else {
      return null;
    }
  }

  /**
   * @effects
   *          save report to a file pcs.txt in the same directory
   *          as the program's
   */
  public void saveReport(String report) {
    String fileName = "pcs.txt";
    writeFile(fileName);
    putln(report);
    writeStandardOutput();
  }

  /**
   * The run method
   * 
   * @effects
   *          initialise an instance of PCProg
   *          create objects from data entered by the user
   *          display a report on the objects
   *          prompt user to save report to file
   *          if user answers "Y"
   *          save report
   *          else
   *          end
   */
  public static void main(String[] args) {
    //
    PCProg prog = new PCProg();

    // create objects
    try {
      prog.createObjects();

      // display report
      String report = prog.displayReport();

      if (report != null) {
        // prompt user to save report
        putln("Save report to file? [Y/N]");
        String toSave = getln();
        if (toSave.equals("Y")) {
          prog.saveReport(report);
          putln("report saved");
        }
      }
    } catch (NotPossibleException e) {
      System.err.printf("%s: %s%n", e, e.getMessage());
    }

    putln("~END~");
  }

  /**
   * @effects
   *          uses PCFactory to create a new PC object and record it in objs
   */
  public void createObjects() {
    Scanner scanner = new Scanner(System.in);
    String input;
    do {
      System.out.print("Enter PC model: ");
      String model = scanner.nextLine();
      System.out.print("Enter PC year: ");
      int year = Integer.parseInt(scanner.nextLine());
      System.out.print("Enter PC manufacturer: ");
      String manufacturer = scanner.nextLine();
      Set<String> comps = new Set<>();
      System.out.println("Enter components: ");
      System.out.println("(Press Enter when blank if you want to stop entering components)");
      while (true) {
        String comp = scanner.nextLine();
        if (comp.isEmpty()) {
          break;
        }
        comps.insert(comp);
      }
      PC pc = PCFactory.getInstance().createPC(model, year, manufacturer, comps);
      getObjects().insert(pc);
      System.out.print("Do you want to continue? (Y/N): ");
      input = scanner.nextLine();
    } while (input.equalsIgnoreCase("Y"));
  }

  /**
   * @effects
   *          return the recorded PC objects
   */
  public Set<PC> getObjects() {
    return this.objs;
  }
}
