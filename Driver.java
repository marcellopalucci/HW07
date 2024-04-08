public class Driver {
    public static void main(String[] args) {

        Member member1 = new Member("Otto", Group.BACKEND, 60);
        Member member2 = new Member("John", Group.ADMIN, 40);
        Member member3 = new Member("Ash", Group.FRONTEND, 15);
        Member member4 = new Member("Ryan", Group.FRONTEND, 37);
        Member member5 = new Member("John", Group.ADMIN, 40);
        Member member6 = new Member("Edy", Group.FRONTEND, 45);

        Member[] teamArr = new Member[]{member1, member2, member3, member4, member5, member6};
//        Member[] teamArr = new Member[]{member1, member2, member3};
        Team team1 = new Team(teamArr);

        System.out.println("Unsorted:");
        for (Member i : teamArr){
            System.out.println(i.toString());
        }

        team1.mergeSortMembers();
        System.out.println("\nSorted:");
        for (int i = 0; i < teamArr.length; i ++){
            System.out.println(team1.getMembers()[i]);
        }
    }
}
