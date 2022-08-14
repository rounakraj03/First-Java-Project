import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

    final int getIntInput(){
        int choice = 0;
        while(choice == 0){
            try{
                choice = reader.nextInt();
                if(choice == 0){
                    throw new InputMismatchException();
                }
                reader.nextLine();
            }catch (InputMismatchException e){
                reader.nextLine();
                System.out.println("ERROR: INVALID INPUT. Please try again:");
            }
        }
        return choice;
    }

    private final void printClubOptions(){
        System.out.println("1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi clubs");
    }

    public int getChoice(){
        int choice;
        System.out.println("WELCOME TO OZONE FITNESS CENTER\n" +
                "================================\n" +
                "1)\tAdd Member\n" +
                "2)\tRemove Member\n" +
                "3)\tDisplay Member Information\n" +
                "\n" +
                "Please select an option (or Enter -1 to quit):\n");

            choice = getIntInput();
            return choice;
    }

    public String addMembers(LinkedList<Member> m){
        String name;
        int club = 0;
        String mem;
        double fees;
        int memberId;
        Member mbr;
        Calculator<Integer> cal;

        System.out.print("Enter name: ");
        name = reader.nextLine();

        printClubOptions();
        club = getIntInput();
        while(club < 1 || club > 4) {
            System.out.println("The value is invalid");
            club = getIntInput();
        }

        if (m.size() > 0){
            memberId = m.getLast().getMemberID() + 1;
       }else{memberId = 1;
        }



        if(club!=4){
            cal = (n)->{
                switch (n){
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                    default:
                        return -1;
                }
            };

            fees = cal.calculateFees(club);
            mbr = new SingleClubMember('S',memberId,name,fees,club);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Single Club Member added\n");
        }else{

            cal = (n)->{
                if(n == 4){return 1200;}
                else{return -1;}
            };

            fees = cal.calculateFees(club);
            mbr = new MultiClubMember('M',memberId,name,fees,100);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Multi Club Member added\n");
        }

        return mem;
    }

    public void removeMember(LinkedList<Member> m){
        int memberID;
        memberID = getIntInput();
        for(int i=0;i<m.size();i++){
            if(m.get(i).getMemberID() == memberID){
                m.remove(i);
                System.out.println("The member has been removed");
                return ;
            }
        }
        System.out.println("The member doesn't found");

    }

    public void printMemberInfo(LinkedList<Member> m){
    int memberID;
    System.out.println("Enter Member ID : ");
    memberID = getIntInput();
    for(int i=0;i<m.size();i++){
        if(m.get(i).getMemberID() == memberID){
            String memberInfo[] = m.get(i).toString().split(", ");
            System.out.println("Member Type = "+ memberInfo[0]);
            System.out.println("Member ID = "+memberInfo[1]);
            System.out.println("Member Name = "+memberInfo[2]);
            System.out.println("Membership Fees = "+memberInfo[3]);
            if(memberInfo[0].equals("S")){
                System.out.println("Club ID = "+memberInfo[4]);
            }else{
                System.out.println("Membership Points = "+memberInfo[4]);
            }
        }
    }
    }

}
