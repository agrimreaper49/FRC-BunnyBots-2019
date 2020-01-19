package us.ilite.common.types;

public enum ETrackingType {

    /*
    Target - 0
    Cargo - 2
    Line - 4
    Add one to prioritize right-hand targets.
    */
    TARGET(1, 0, true),
    BALL(2, 0, false),
    TARGET_ZOOM( 3, 0, true),
    // CARGO(3, 0, true),
    /*LINE_LEFT(4, -1, false),
    LINE_RIGHT(5, 1, false),*/
   // LINE(5, 0, false),
    NONE(0, 0, false);

    private final int kPipelineNum;
    private final boolean kLedOn;
    private final int kTurnScalar;

    ETrackingType(int pPipelineNum, int pTurnScalar, boolean pLedOn) {
        kPipelineNum = pPipelineNum;
        kTurnScalar = pTurnScalar;
        kLedOn = pLedOn;
    }

    public int getPipeline() {
        return kPipelineNum;
    }

    public int getTurnScalar() {
        return kTurnScalar;
    }

    public boolean getLedOn() {
        return kLedOn;
    }

}