

### **Strategy Pattern**

In software development, we often face situations where an object needs to perform a task, but the way it performs that task may change depending on circumstances.
Instead of hard-coding all the possible ways inside one class, the **Strategy Pattern** allows us to define a family of algorithms (or behaviors), put each into its own class, and make them interchangeable at runtime.

#### **What It Does**

The Strategy Pattern enables you to:

* Define a set of related algorithms or behaviors (called strategies).
* Encapsulate each behavior in a separate class.
* Let the main object (called the context) use these behaviors without knowing their details.
* Change the behavior dynamically while the program is running.

#### **When to Use**

Use the Strategy Pattern when:

* You have multiple ways to perform a task (e.g., payment by card, PayPal, or cash).
* You want to avoid long `if-else` or `switch` statements.
* You expect the behavior to change at runtime (e.g., switching sorting algorithms, discount methods, or user roles).

#### **Structure**

1. **Strategy Interface** – Defines the common behavior (e.g., `TravelStrategy` with `travel()` method).
2. **Concrete Strategies** – Different implementations (e.g., `CarStrategy`, `TrainStrategy`, `FlightStrategy`).
3. **Context Class** – Holds a reference to a Strategy and delegates the work to it.

---
example codes
File Path : src\application1\Application1.java
/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package application1;/** * * @author ASUS */public class Application1 {    /**     * @param args the command line arguments     */    public static void main(String[] args) {        // TODO code application logic here        students ug = new undergraduate();        ug.setProgram(new Bsc());        ug.setFestival(new codeFest());        ug.showEnrollment();        ug.participate(1); // 1st place in CodeFest = 100 points        ug.currentState();        System.out.println();        // Switch festival at runtime        ug.setFestival(new RoboFest());        ug.participate(3); // 3rd place in RoboFest = 60 points        ug.currentState();        System.out.println();        // Postgraduate example        students pg = new postgraduate();        pg.setProgram(new Msc());        pg.setFestival(new GameFest());        pg.showEnrollment();        pg.participate(2); // 2nd place in GameFest = 65 points        pg.currentState();        System.out.println();        // Upgrade program and switch festival        pg.setProgram(new Doctoral());        pg.setFestival(new codeFest());        pg.showEnrollment();        pg.participate(0); // participation = 15 points        pg.currentState();    }        }

File Path : src\application1\Bsc.java
/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package application1;/** * * @author ASUS */public class Bsc implements program{      @Override    public String name() {         return "BSc";     }}

File Path : src\application1\codeFest.java
/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package application1;/** * * @author ASUS */public class codeFest implements compitition {      @Override    public String name() {        return "CodeFest";     }      @Override    public int pointsFor(int rank) {        switch (rank) {             case 1 : return 100;            case 2 : return 70;             case 3 : return 50;             default :return 15;         }    }}

File Path : src\application1\compitition.java
/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package application1;/** * * @author ASUS */public interface compitition {    String name();                    int pointsFor(int rank);}

File Path : src\application1\Doctoral.java
/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package application1;/** * * @author ASUS */public class Doctoral implements program{    @Override    public String name() {        return "Doctoral";    }    }

File Path : src\application1\GameFest.java
/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package application1;/** * * @author ASUS */public class GameFest implements compitition{     @Override    public String name() {         return "GameFest";    }       @Override    public int pointsFor(int rank) {        switch (rank) {            case 1:return 90;            case 2 :return 65;            case 3 :return 45;            default:return 25;        }    }}

File Path : src\application1\Msc.java
/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package application1;/** * * @author ASUS */public class Msc implements program {  @Override     public String name() {         return "MSc"; }}

File Path : src\application1\postgraduate.java
/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package application1;/** * * @author ASUS */public class postgraduate extends students {    public postgraduate() {         super("Postgraduate Student"); }}    

File Path : src\application1\program.java
/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package application1;/** * * @author ASUS */public interface program {    String name();}

File Path : src\application1\RoboFest.java
/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package application1;/** * * @author ASUS */public class RoboFest implements compitition{    @Override    public String name() { return "RoboFest"; }      @Override    public int pointsFor(int rank) {        switch (rank) {            case 1: return 110;            case 2:return 80;            case 3:return 60;            default: return 20;        }    }}

File Path : src\application1\students.java
/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package application1;/** * * @author ASUS */public abstract class students {    private final String type;      // "Undergraduate Student" or "Postgraduate Student"    private program program;    private compitition festival;    private int totalPoints = 0;    protected students(String type) {        this.type = type;     }    // Dynamic setters (swap at runtime)    public void setProgram(program program)   {         this.program = program;    }    public void setFestival(compitition festival){         this.festival = festival;     }    // Behaviors    public void showEnrollment() {        System.out.printf("%s enrolled in %s program.%n",                type, program == null ? "-" : program.name());    }    public void participate(int rank) {        if (festival == null) {            System.out.println("No festival selected.");            return;        }        int pts = festival.pointsFor(rank);        totalPoints += pts;        System.out.printf("%s participated in %s (rank %s) → +%d points. Total = %d%n",                type, festival.name(), rankLabel(rank), pts, totalPoints);    }    public void currentState() {        System.out.printf("%s → Program: %s | Competition: %s | Points: %d%n",                type,                program == null ? "-" : program.name(),                festival == null ? "-" : festival.name(),                totalPoints);    }    private String rankLabel(int rank) {        switch (rank) {            case 1:  return "1st";            case 2:  return "2nd";            case 3:  return "3rd";            default: return "participation";        }    }   }

File Path : src\application1\undergraduate.java
/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package application1;/** * * @author ASUS */public class undergraduate extends students{    public undergraduate() {         super("Undergraduate Student");     }}    

---

### **Implementation Steps**

#### **Step 1**

Identify goals:
The system must handle two types of students, multiple programs, and competitions with different point rules.
Students can switch programs and festivals at runtime.

#### **Step 2**

Identify what may vary:

* Academic programs (BSc → MSc → Doctoral)
* Competition rules (CodeFest, RoboFest, GameFest)
  These should be separated as strategies.

#### **Step 3**

Define interfaces:

* `ProgramStrategy` → represents academic enrollment
* `CompetitionStrategy` → represents scoring rules

#### **Step 4**

Implement concrete strategies:

* Programs: `BScProgram`, `MScProgram`, `DoctoralProgram`
* Festivals: `CodeFest`, `RoboFest`, `GameFest`

#### **Step 5**

Create an **abstract `Student` class** (context).
It holds:

* a `ProgramStrategy`
* a `CompetitionStrategy`
  It delegates work to these and tracks total points.

#### **Step 6**

Extend `Student` into:

* `UndergraduateStudent`
* `PostGraduateStudent`

#### **Step 7**

Add setter methods like `setProgram()` and `setFestival()` for dynamic switching at runtime.

#### **Step 8**

Demonstrate in `main()`:

* Create students
* Assign initial programs/festivals
* Simulate competitions
* Show switching and point updates dynamically

---

### **Group Project Task**

1. Form your pre-assigned 6-member groups.
2. Continue with your previously assigned project topic.
3. Incorporate at least **one design pattern** that fits your project.

---

### **Submission Checklist**

#### **1. Report**

Include:

* **Group Details** (members + student IDs)
* **Design Pattern(s) Used**
* **Justification** (why chosen and how it improves design)
* **Screenshots of Implementation** (code snippets or outputs)

#### **2. Code Files**

Ensure:

* Proper class structure (Main, Context, Concrete, Interfaces)
* Clear naming and comments
* Functional demonstration of the design pattern

