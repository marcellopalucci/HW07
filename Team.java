public class Team {
    private Member[] members;

    /**
     * Constructor which takes in and array of Members.
     * @param members array of Member instances that store all the members of
     *                your team.
     */
    public Team(Member[] members){
        int memberSize = members.length;
        this.members = new Member[memberSize];
        for(int i = 0; i < memberSize; i++){
            this.members[i] = members[i];
        }
    }

    /**
     * The mergeSortMembers method uses the merge sort algorithm
     * to sort the Members array.
     */
    public void mergeSortMembers() {
        if (members.length  > 1){
            Member[] firstHalf = new Member[members.length / 2];
            HWUtils.copyOfRange(members, 0, members.length/2);
        }
    }
}
