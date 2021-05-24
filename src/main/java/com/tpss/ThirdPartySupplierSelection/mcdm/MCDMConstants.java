package com.tpss.ThirdPartySupplierSelection.mcdm;

public class MCDMConstants {

    //main criteria weights
    public static final Double C1 = 0.191;
    public static final Double C2 = 0.226;
    public static final Double C3 = 0.181;
    public static final Double C4 = 0.204;

    public static final String c1Name ="Technological Capabilities";
    public static final String c2Name ="Service Capabilities";
    public static final String c3Name ="Business Excellence";
    public static final String c4Name ="Sustainability";
    public static final String c5Name ="Cost";


    //global weight for subcriterias
    public static final Double c11 = 0.058;
    public static final Double c12 = 0.049;
    public static final Double c13 = 0.040;
    public static final Double c14 = 0.044;

    public static final Double c21 = 0.063;
    public static final Double c22 = 0.062;
    public static final Double c23 = 0.050;
    public static final Double c24 = 0.051;

    public static final Double c31 = 0.050;
    public static final Double c32 = 0.051;
    public static final Double c33 = 0.044;
    public static final Double c34 = 0.054;

    public static final Double c41 = 0.072;
    public static final Double c42 = 0.053;
    public static final Double c43 = 0.056;

    public static final Double c51 = 0.119;
    public static final Double c52 = 0.085;

    public static final Double[] weights = {c11,c12,c13,c14,c21,c22,c23,c24,c31,c32,c33,c34,c41,c42,c43,c51,c52};

    public static final int INDEX_COLD_TRACEABILITY = 0;
    public static final int INDEX_TEMPERATURE_MANAGEMENT = 1;
    public static final int INDEX_PACKAGING_LABELING = 2;
    public static final int INDEX_LOGISTICS_APPROACH = 3;
    public static final int INDEX_DELIVERY_SPEED = 4;
    public static final int INDEX_DISTRIBUTION_COVERAGE = 5;
    public static final int INDEX_VEHICLE_CAPACITY = 6;
    public static final int INDEX_CONDITION_ON_ARRIVAL = 7;
    public static final int INDEX_FINANCIAL_STABILITY = 8;
    public static final int INDEX_EXPERIENCE = 9;
    public static final int INDEX_HUMAN_RES = 10;
    public static final int INDEX_PARTNERSHIP = 11;
    public static final int INDEX_ENVIRONMENTAL_AWARENESS = 12;
    public static final int INDEX_ETHICAL_RESPONSIBILITY = 13;
    public static final int INDEX_GOVERNANCE = 14;
    public static final int INDEX_DIRECT_COSTS = 15;
    public static final int INDEX_INDIRECT_COSTS = 16;
}
