public class MultiClubMember extends Member{
    private int membershipPoints;


    public MultiClubMember(char pMemberType, int pMemberID, String pName, double pFees,int pMembershipPoints) {
        super(pMemberType, pMemberID, pName, pFees);
        membershipPoints = membershipPoints;
    }

    public int getMembershipPoints(){
        return membershipPoints;
    }

    public void setMembershipPoints(int pMembershipPoints) {
        membershipPoints = membershipPoints;
    }


    public String toString(){
        return super.toString() +", "+membershipPoints;
    }

}
