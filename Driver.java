import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {

        Member member1 = new Member("Otto", Group.BACKEND, 60);
        Member member2 = new Member("John", Group.ADMIN, 50);
        Member member3 = new Member("Ash", Group.FRONTEND, 15);
        Member member4 = new Member("Ryan", Group.FRONTEND, 37);
        Member member5 = new Member("John", Group.ADMIN, 40);
        Member member6 = new Member("Edy", Group.FRONTEND, 45);
        Member fakeMember = new Member("Lionel", Group.ADMIN, 30);

        Member[] teamArr = new Member[]{member1, member2, member3, member4, member5, member6};
        Member[] teamArr1 = new Member[]{member1, member2, member3};
        Member[] teamArr2 = new Member[]{member6, member3, member5};
        Member[][] twoDTeam = new Member[][] {teamArr1, teamArr2};

        Team team1 = new Team(teamArr);

        System.out.println("Unsorted:");
        printArray(team1);

        team1.mergeSortMembers();
        System.out.println("\nSorted:");
        printArray(team1);

        System.out.println("\nFound:");
        System.out.println(team1.searchMember(member1));
        //System.out.println(team1.searchMember(fakeMember));


        Team reversedTeam = new Team(team1.reverseMembers());
        System.out.println("\nReversed:");
        printArray(reversedTeam);

        ArrayList<Member> leaders = team1.selectLeaderBoard();
        System.out.println("\nLeaders:");
        System.out.println(leaders.get(0));
        System.out.println(leaders.get(1));

        team1.mergeAllMembers(twoDTeam);
        System.out.println("\nMerge all members:");
        printArray(team1);
    }

    private static void printArray(Team t) {
        int size = t.getMembers().length;
        for (Member i : t.getMembers()){
            System.out.println(i);
        }
    }

}
