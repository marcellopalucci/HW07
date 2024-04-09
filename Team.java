import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

public class Team {
    private Member[] members;
    /**
     * Constructor which takes in and array of Members.
     * @param members array of Member instances that store all the members of
     *                your team.
     */
    public Team(Member[] members){
        int memberSize = members.length;
        //shallow copy
        //this.members = members;
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
        members = recursiveMergeMembers(members);
    }
    private Member[] recursiveMergeMembers(Member[] original){
        if(original.length > 1){
            int length = original.length;
            int middle = length / 2;

            Member[] leftMembers =  HWUtils.copyOfRange(original, 0, middle);
            Member[] rightMembers = HWUtils.copyOfRange(original, middle, length);

            leftMembers = recursiveMergeMembers(leftMembers);
            rightMembers = recursiveMergeMembers(rightMembers);

            return HWUtils.merge(leftMembers, rightMembers);
        }
        return original;
    }

    public void mergeAllMembers(Member[][] twoDMembers) {
        mergeSortMembers();
        int i = 0;
        members = recursiveMergeAllMembers(twoDMembers, members, i);
        mergeSortMembers();
    }
    public Member[] recursiveMergeAllMembers(Member[][] twoDMembers, Member[] original, int i){
        if (i < twoDMembers.length - 1){
            Member[] mergedMembers = HWUtils.merge(twoDMembers[i], twoDMembers[i + 1]);
            recursiveMergeAllMembers(twoDMembers, mergedMembers, ++i);
        }
        return HWUtils.merge(members, original);
    }

    public Member searchMember(Member m) {
        if (m == null){
            throw new IllegalArgumentException("Member cannot be null");
        }
        mergeSortMembers();
        int low = 0;
        int high = members.length - 1;

        int foundIndex = recursiveSearchMember(low, high, m);
        if (foundIndex == -1){
            throw new NoSuchElementException("Member not found in the member array");
        }else {
            return members[foundIndex] ;
        }
    }

    private int recursiveSearchMember(int low, int high, Member key) {
        int mid = (high + low) / 2;
        if (low > high){
            return -1;
        }
        if (members[mid].equals(key)){
            return mid;
        } else if (members[mid].compareTo(key) < 0) {
            return recursiveSearchMember(mid + 1, high, key);
        } else {
            return recursiveSearchMember(low, mid - 1, key);
        }
    }
    public Member[] reverseMembers() {
        Member[] reversedMembers = new Member[members.length];
        int i = members.length - 1;
        return recursiveReverseMembers(reversedMembers, i);
    }

    private Member[] recursiveReverseMembers(Member[] reversedMembers, int i){
        if (i >= 0){
            reversedMembers[members.length - i - 1] = members[i];
            recursiveReverseMembers(reversedMembers, --i);
        }
        return reversedMembers;
    }

    public ArrayList<Member> selectLeaderBoard(){
        ArrayList<Member> backEndGroup = new ArrayList<>();
        ArrayList<Member> frontEndGroup = new ArrayList<>();
        int i =0;

        return recursiveLeaderBoard(backEndGroup, frontEndGroup, i);
    }

    private ArrayList<Member> recursiveLeaderBoard(ArrayList<Member> backEnd, ArrayList<Member> frontEnd, int i){
        if (i < members.length){
            if (members[i].getSubgroup() == Group.BACKEND) {
                backEnd.add(members[i]);
                return recursiveLeaderBoard(backEnd, frontEnd, ++i);
            } else if (members[i].getSubgroup() == Group.FRONTEND) {
                frontEnd.add(members[i]);
                return recursiveLeaderBoard(backEnd, frontEnd, ++i);
            } else {
                return recursiveLeaderBoard(backEnd, frontEnd, ++i);
            }
        }

        if (backEnd.isEmpty() && frontEnd.isEmpty()){
            throw new NoSuchElementException("Failed to select leaders for both subgroups.");
        } else if (frontEnd.isEmpty()) {
            throw new NoSuchElementException("Failed to select a leader for the FRONTEND subgroup.");
        } else if (backEnd.isEmpty()) {
            throw new NoSuchElementException("Failed to select a leader for the BACKEND subgroup.");
        }
        ArrayList<Member> leaders = new ArrayList<>(2);
        int frontEndSelector = (int) (Math.random() * frontEnd.size());
        int backEndSelector = (int) (Math.random() * backEnd.size());
        leaders.add(frontEnd.get(frontEndSelector));
        leaders.add(backEnd.get(backEndSelector));

        return leaders;
    }
    public Member[] getMembers() {
        return members;
    }
}
