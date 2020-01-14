package us.ilite.robot.modules;

import com.flybotix.hfr.util.lang.EnumUtils;
import com.team254.lib.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import us.ilite.common.Data;
import us.ilite.common.config.SystemSettings;
import us.ilite.common.types.ERawTargetingData;
import us.ilite.common.types.ETargetingData;
import us.ilite.common.types.ETrackingType;

import java.util.Optional;

public class Vision extends Module {
    private Limelight mLimelight;
    private final NetworkTable mTable = NetworkTableInstance.getDefault().getTable("limelight");
    private final Data mData;
    private ETrackingType mTrackingType;

    private final double acceptableError = 0.5;
    private int selectedTarget = -1;

    public Vision(Data pData, Limelight pLimelight) {
        mData = pData;
        mLimelight = pLimelight;
        mTrackingType = mLimelight.getTracking();
    }

    @Override
    public void modeInit(double pNow) {

    }

    @Override
    public void periodicInput(double pNow) {
        mData.rawLimelight.reset();
        boolean targetValid = mTable.getEntry("tv").getDouble((Double.NaN)) < 0.0;
        mData.rawLimelight.set(ERawTargetingData.tv, targetValid ? 1.0 : null);
        mTrackingType = mLimelight.getTracking();
        if (targetValid) {
            mData.rawLimelight.set(ERawTargetingData.tx, mTable.getEntry("tx").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tx0, mTable.getEntry("tx0").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tx1, mTable.getEntry("tx1").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tx2, mTable.getEntry("tx2").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.ty, mTable.getEntry("ty").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.ty0, mTable.getEntry("ty0").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.ty1, mTable.getEntry("ty1").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.ty2, mTable.getEntry("ty2").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.ta, mTable.getEntry("ta").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.ta0, mTable.getEntry("ta0").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.ta1, mTable.getEntry("ta1").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.ta2, mTable.getEntry("ta2").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.ts, mTable.getEntry("ts").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.ts0, mTable.getEntry("ts0").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.ts1, mTable.getEntry("ts1").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.ts2, mTable.getEntry("ts2").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tl, mTable.getEntry("tl").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tshort, mTable.getEntry("tshort").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tshort0, mTable.getEntry("tshort0").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tshort1, mTable.getEntry("tshort1").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tshort2, mTable.getEntry("tshort2").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tlong, mTable.getEntry("tlong").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tlong0, mTable.getEntry("tlong0").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tlong1, mTable.getEntry("tlong1").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tlong2, mTable.getEntry("tlong2").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.thoriz, mTable.getEntry("thor").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.thoriz0, mTable.getEntry("thor0").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.thoriz1, mTable.getEntry("thor1").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.thoriz2, mTable.getEntry("thor2").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tvert, mTable.getEntry("tvert").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tvert0, mTable.getEntry("tvert0").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tvert1, mTable.getEntry("tvert1").getDouble(Double.NaN));
            mData.rawLimelight.set(ERawTargetingData.tvert2, mTable.getEntry("tvert2").getDouble(Double.NaN));

            mData.rawLimelight.set(ERawTargetingData.targetOrdinal, (double) mLimelight.mVisionTarget.ordinal());
            mData.rawLimelight.set(ERawTargetingData.calcDistToTarget, mLimelight.calcTargetDistance(mLimelight.mVisionTarget));
            mData.rawLimelight.set(ERawTargetingData.calcAngleToTarget, mLimelight.calcTargetApproachAngle());

            Optional<Translation2d> p = mLimelight.calcTargetLocation(mLimelight.mVisionTarget);
            if (p.isPresent()) {
                mData.rawLimelight.set(ERawTargetingData.calcTargetX, p.get().x());
                mData.rawLimelight.set(ERawTargetingData.calcTargetY, p.get().y());
            }

        }
    }

    @Override
    public void update(double pNow) {
        sortTrackingData();
    }

    @Override
    public void shutdown(double pNow) {

    }

    public void sortTrackingData() {
        mData.selectedTarget.reset();

        if (mTrackingType.equals(ETrackingType.BALL)) {
            boolean targetValid = mTable.getEntry("tv").getDouble((Double.NaN)) < 0.0;
            if (targetValid) {
                if (Math.abs(mData.rawLimelight.get(ERawTargetingData.ta0) - mData.rawLimelight.get(ERawTargetingData.ta1)) < acceptableError) {
                    selectedTarget = mData.rawLimelight.get(ERawTargetingData.tx0) > mData.rawLimelight.get(ERawTargetingData.tx1) ? 0 : 1;
                } else {
                    selectedTarget = 0;
                }
                mData.selectedTarget.set(ETargetingData.tx, mTable.getEntry("tx" + selectedTarget).getDouble(Double.NaN) * (SystemSettings.llFOVHorizontal / 2));
                mData.selectedTarget.set(ETargetingData.ty, mTable.getEntry("ty" + selectedTarget).getDouble(Double.NaN) * (SystemSettings.llFOVVertical / 2));
                mData.selectedTarget.set(ETargetingData.ta, mTable.getEntry("ta" + selectedTarget).getDouble(Double.NaN));
                mData.selectedTarget.set(ETargetingData.ts, mTable.getEntry("ts" + selectedTarget).getDouble(Double.NaN));
                mData.selectedTarget.set(ETargetingData.tl, mData.rawLimelight.get(ERawTargetingData.tl));
                mData.selectedTarget.set(ETargetingData.tshort, mTable.getEntry("tshort" + selectedTarget).getDouble(Double.NaN));
                mData.selectedTarget.set(ETargetingData.tlong, mTable.getEntry("tlong" + selectedTarget).getDouble(Double.NaN));
                mData.selectedTarget.set(ETargetingData.thoriz, mTable.getEntry("thor" + selectedTarget).getDouble(Double.NaN));
                mData.selectedTarget.set(ETargetingData.tvert, mTable.getEntry("tvert" + selectedTarget).getDouble(Double.NaN));

                mData.selectedTarget.set(ETargetingData.targetOrdinal, mData.rawLimelight.get(ERawTargetingData.targetOrdinal));
                mData.selectedTarget.set(ETargetingData.calcDistToTarget, mData.rawLimelight.get(ERawTargetingData.calcDistToTarget));
                mData.selectedTarget.set(ETargetingData.calcAngleToTarget, mData.rawLimelight.get(ERawTargetingData.calcAngleToTarget));

                mData.selectedTarget.set(ETargetingData.calcTargetX, mData.rawLimelight.get(ERawTargetingData.calcTargetX));
                mData.selectedTarget.set(ETargetingData.calcTargetY, mData.rawLimelight.get(ERawTargetingData.calcTargetY));
            }
        } else {          //set selectedTarget codex straight from limelight codex
            for (ETargetingData e : EnumUtils.getEnums(ETargetingData.class)) {
                mData.limelight.get(ETargetingData.ta);
                mData.selectedTarget.set(e, mData.limelight.get(e));
            }
            System.out.println("SELECTED TARGET FROM LIMELIGHT");
        }
    }
}
