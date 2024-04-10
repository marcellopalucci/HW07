import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Team is a class that holds the recursive methods to easily manage a team.
 * @author mpalucci3
 * @version 1.2
 */
public class Team {
    private Member[] members;
    /**
     * Constructor which takes in and array of Members and makes a deep copy of the passed in array.
     * @param members array of Member instances that store all the members of
     *                your team.
     */
    public Team(Member[] members) {
        int memberSize = members.length;
        this.members = new Member[memberSize];
        for (int i = 0; i < memberSize; i++) {
            this.members[i] = members[i];
        }

    }

    /**
     * The mergeSortMembers method uses the merge sort algorithm
     * to sort the Members array in non-decreasing order. This method does not take any arguments nor
     * return anything. The mergeSortMembers calls a recursive helper method
     * to apply the merge sort algorithm to the members array.
     */
    public void mergeSortMembers() {
        members = recursiveMergeMembers(members);
    }
    private Member[] recursiveMergeMembers(Member[] original) {
        if (original.length > 1) {
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

    /**
     * This method takes in a 2-D array of Member instances and does not return anything.
     * The mergeAllMembers calls a recursive helper method to merge and sort the 2-D array passed in
     * with the original members array.
     * @param twoDMembers 2-D array representing Member instances to be merged and sorted
     */
    public void mergeAllMembers(Member[][] twoDMembers) {
        mergeSortMembers();
        int i = 0;
        members = recursiveMergeAllMembers(twoDMembers, members, i);
        mergeSortMembers();
    }
    private Member[] recursiveMergeAllMembers(Member[][] twoDMembers, Member[] original, int i) {
        if (i < twoDMembers.length - 1) {
            Member[] mergedMembers = HWUtils.merge(twoDMembers[i], twoDMembers[i + 1]);
            recursiveMergeAllMembers(twoDMembers, mergedMembers, ++i);
        }
        return HWUtils.merge(members, original);
    }

    /**
     * This method takes in a Member instance and returns the Member instance in the members
     * array that has the same name, subgroup, and hoursWorked.
     * The searchMember array calls a recursive helper method recursively search for the member
     * being searched for in the members array.
     * @param m Member representing a member in the team to be searched for in the
     * @return Member representing the found member
     */
    public Member searchMember(Member m) {
        if (m == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        mergeSortMembers();
        int low = 0;
        int high = members.length - 1;

        int foundIndex = recursiveSearchMember(low, high, m);
        if (foundIndex == -1) {
            throw new NoSuchElementException("Member not found in the member array");
        } else {
            return members[foundIndex];
        }
    }

    private int recursiveSearchMember(int low, int high, Member key) {
        int mid = (high + low) / 2;
        if (low > high) {
            return -1;
        }
        if (members[mid].equals(key)) {
            return mid;
        } else if (members[mid].compareTo(key) < 0) {
            return recursiveSearchMember(mid + 1, high, key);
        } else {
            return recursiveSearchMember(low, mid - 1, key);
        }
    }

    /**
     * This method does not take in any arguments and returns a deep copy of the members array in reversed sorted order.
     * The reverseMembers method calls a recursive method which recursively reverses the order of the members array.
     * @return Member[] representing the array in reverse order
     */
    public Member[] reverseMembers() {
        Member[] reversedMembers = new Member[members.length];
        int i = members.length - 1;
        return recursiveReverseMembers(reversedMembers, i);
    }

    private Member[] recursiveReverseMembers(Member[] reversedMembers, int i) {
        if (i >= 0) {
            reversedMembers[members.length - i - 1] = members[i];
            recursiveReverseMembers(reversedMembers, --i);
        }
        return reversedMembers;
    }

    /**
     * This method takes in no arguments, and returns a randomly selected leader for the
     * BACKEND and FRONTEND group. The selectLeaderBoard calls a recursive helper method that recursively
     * populates a BACKEND and FRONTEND ArrayList, to then randomly select a leader for each group.
     * @return ArrayList of type Member, where index 0 represents the leader for the FRONTEND group
     *         and index 1 represents the learder for the BACKEND group
     */
    public ArrayList<Member> selectLeaderboard() {
        ArrayList<Member> backEndGroup = new ArrayList<>();
        ArrayList<Member> frontEndGroup = new ArrayList<>();
        int i = 0;

        return recursiveLeaderBoard(backEndGroup, frontEndGroup, i);
    }

    private ArrayList<Member> recursiveLeaderBoard(ArrayList<Member> backEnd, ArrayList<Member> frontEnd, int i) {
        if (i < members.length) {
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

        if (backEnd.isEmpty() && frontEnd.isEmpty()) {
            throw new NoSuchElementException("Failed to select leaders for both subgroups.");
        } else if (frontEnd.isEmpty()) {
            throw new NoSuchElementException("Failed to select a leader for the FRONTEND subgroup.");
        } else if (backEnd.isEmpty()) {
            throw new NoSuchElementException("Failed to select a leader for the BACKEND subgroup.");
        }
        ArrayList<Member> leaders = new ArrayList<>(2);
        int frontEndSelector = new Random().nextInt(frontEnd.size());
        int backEndSelector = new Random().nextInt(backEnd.size());
        leaders.add(frontEnd.get(frontEndSelector));
        leaders.add(backEnd.get(backEndSelector));

        return leaders;
    }

    /**
     * Getter method to return the members stored of a team.
     * @return members array of Member instances that store all the members of a team.
     */
    public Member[] getMembers() {
        return members;
    }

    /**
     * Setter method to set the members array.
     * @param members array of Member instances that store all the members of a team.
     */
    public void setMembers(Member[] members) {
        this.members = members;
    }
}
