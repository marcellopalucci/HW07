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

        if(members.length > 1){
            int length = members.length;
            int middle = length / 2;

            //Member[] leftArray = new Member[middle];
            //Member[] rightArray = new Member[length - middle];

            Member[] leftMembers = HWUtils.copyOfRange(this.members, 0, middle);
            Member[] rightMembers = HWUtils.copyOfRange(this.members, middle, length);

            Team leftTeam = new Team(leftMembers);
            Team rightTeam = new Team(rightMembers);

            leftTeam.mergeSortMembers();
            rightTeam.mergeSortMembers();

            mergeSki(this.members, leftMembers, rightMembers);

            //mergeSort(leftMembers);
            //mergeSort(rightMembers);
//            Member[] mergedLeftHalf = mergeOnSplit(leftMembers);
//            Member[] mergedRightHalf = mergeOnSplit(rightMembers);

           // setMembers(HWUtils.merge(leftMembers, rightMembers));
        //    this.members = HWUtils.merge(leftMembers, rightMembers);
        }
    }
    private static void mergeSki(Member[] original, Member[] left, Member[] right){
        original = HWUtils.merge(left, right);
    }
    private void mergeSort(Member[] members) {
        mergeSortMembers();
            Team newTeam = new Team(members);
//            newTeam.mergeSortMembers();
        }

    public Member[] getMembers () {
        return members;
    }

    public void setMembers(Member[] members) {
        this.members = members;
    }
}
