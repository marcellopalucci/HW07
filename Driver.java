public class Driver {
    public static void main(String[] args) {
        Member member1 = new Member("John", Group.ADMIN, 40);
        Member member2 = new Member("Ottavio", Group.BACKEND, 60);
        Member member3 = new Member("Ryan", Group.FRONTEND, 37);
        Member member4 = new Member("John", Group.ADMIN, 40);
        Member[] teamArr = new Member[]{member1, member2, member3, member4};
        Team team1 = new Team(teamArr);

        System.out.println(member1.equals(member4));

        for (Member i : teamArr){
            System.out.println(i.toString());
        }
    }
}
