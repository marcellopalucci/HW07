import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {

        Member member1 = new Member("Otto", Group.BACKEND, 60);
        Member member2 = new Member("John", Group.ADMIN, 40);
        Member member3 = new Member("Ash", Group.FRONTEND, 15);
        Member member4 = new Member("Ryan", Group.FRONTEND, 37);
        Member member5 = new Member("John", Group.ADMIN, 40);
        Member member6 = new Member("Edy", Group.FRONTEND, 45);

        Member fakeMember = new Member("Lionel", Group.ADMIN, 30);

        Member[] teamArr = new Member[]{member1, member2, member3, member4, member5, member6};
        Member[] teamArr1 = new Member[]{member1, member2, member3};
        Member[] teamArr2 = new Member[]{member6, member3, member5};
        Member[][] twoDTeam = new Member[][] {{member1, member2, member3}, {member1, member2, member3}};
        Team team1 = new Team(teamArr);

        System.out.println("Unsorted:");
        for (int i = 0; i < teamArr.length; i++) {
            System.out.println(team1.getMembers()[i]);
        }

        team1.mergeSortMembers();
        System.out.println("\nSorted:");
        for (int i = 0; i < teamArr.length; i++) {
            System.out.println(team1.getMembers()[i]);
        }
        System.out.println("\nFound:");
        System.out.println(team1.searchMember(member1));

        Team reversedTeam = new Team(team1.reverseMembers());
        System.out.println("\nReversed:");
        for (int i = 0; i < teamArr.length; i++) {
            System.out.println(reversedTeam.getMembers()[i]);
        }

        ArrayList<Member> leaders = team1.selectLeaderBoard();
        System.out.println("Leaders:");
        System.out.println(leaders.get(0));
        System.out.println(leaders.get(1));

        team1.mergeAllMembers(twoDTeam);
    }
}
