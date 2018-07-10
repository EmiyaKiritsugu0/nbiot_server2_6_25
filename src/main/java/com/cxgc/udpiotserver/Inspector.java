package com.cxgc.udpiotserver;


import com.cxgc.Database.DAO.IIDao;
import com.cxgc.Database.model.InspectorInformation;


import java.util.LinkedList;


/**
 * Created by HeRui on 2018/5/24.
 */
public class Inspector {

    private static final int GPSInspectorsCapacity = 60;
    private  LinkedList<GPSInspector> GPSInspectors = new LinkedList<GPSInspector>();
//    private static List<GPSInspector> GPSInspectors = new ArrayList<>(GPSInspectorsCapacity);
    private  int GPSInspectorFlag = 0;
    private static double longitudeInterval = 0.3;
    private static double latitudeInterval = 0.3;
    private static double projectLongitude = 120.234;
    private static double progectLatitude = 30.4244;
    private static int GPSWarning = 45;

    private static final int FuelInspectorsCapacity = 10;
    private  LinkedList<FuelInspector> fuelInspectors = new LinkedList<FuelInspector>();
//  private static List<FuelInspector> fuelInspectors = new ArrayList<>(FuelInspectorsCapacity);
    private  int fuelInspectorFlag = 0;
    private static int fuelWarning = 8;
    private static double fuelInterval = 0.1;
    private static double speedLimit = 5;


    private static final int rotationInspectorsCapacity = 10;
    private  LinkedList<RotationInspector> rotationInspectors = new LinkedList<RotationInspector>();
//  private static List<RotationInspector> rotationInspectors = new ArrayList<>(rotationInspectorsCapacity);
    private static int rotationInspectorFlag = 0;
    private static int rotationDirectionWarning = 5;
    private static int rotationPositionWarning = 5;

    private GPSInspector gpsInspector ;
    private FuelInspector fuelInspector ;
    private RotationInspector rotationInspector ;

//    public Inspector(GPSInspector gpsInspector,FuelInspector fuelInspector,RotationInspector rotationInspector){
//        this.gpsInspector = gpsInspector;
//        this.fuelInspector = fuelInspector;
//        this.rotationInspector = rotationInspector;
//    }



    public void updateInspector(GPSInspector gpsInspector,FuelInspector fuelInspector,RotationInspector rotationInspector){
        if(!gpsInspector.equals(null)) {
            updateGPSInspectors(gpsInspector);
        }
        if(!fuelInspector.equals(null)) {
            updateFuelInspectors(fuelInspector);
        }
        if(!gpsInspector.equals(null)) {
            updateRotationInspectors(rotationInspector);
        }
    }

//   public  void updateGPSInspectors(GPSInspector gpsInspector){
//
//        if(GPSInspectors.size()<= GPSInspectorsCapacity)
//        {
//            if(GPSInspectors.contains(gpsInspector)||new Date(gpsInspector.getDate().getTime()).before(new Date(DaoUtil.strToDate("2018-1-0").getTime()))){
//                // System.out.println("lueluelue");
//            }else{
//                GPSInspectors.add(gpsInspector);
//            }
//
//
//
//        }else {
//
//            if(GPSInspectors.contains(gpsInspector)||new Date(gpsInspector.getDate().getTime()).before(new Date(DaoUtil.strToDate("2018-1-0").getTime()))){
//
//                //System.out.println("lueluelue");
//            }else{
//                GPSInspectors.set(GPSInspectorFlag,gpsInspector);
//                GPSInspect(GPSInspectors,GPSInspectorFlag);
//                GPSInspectorFlag++;
//                if(GPSInspectorFlag == GPSInspectorsCapacity){
//                    GPSInspectorFlag = 0;
//                }
//
//
//            }
//
//        }
//    }

    public  void updateGPSInspectors(GPSInspector gpsInspector){
        boolean processed = false;
        if(GPSInspectors.size()< GPSInspectorsCapacity)
        {

            GPSInspectors.offer(gpsInspector);

            if(GPSInspectorFlag < GPSInspectorsCapacity){
                GPSInspectorFlag += 1;
            }


        }else {

            GPSInspectors.poll();
            GPSInspectors.offer(gpsInspector);
            if(GPSInspectorFlag == GPSInspectorsCapacity){
                GPSInspect(GPSInspectors);
                GPSInspectorFlag = 0;
                processed = true;
            }
            if(!processed){
                if(GPSInspectorFlag < GPSInspectorsCapacity){
                    GPSInspectorFlag += 1;
                }
            }

        }
    }
//    private  void  GPSInspect(List<GPSInspector> GPSInspectors , int GPSInspectorFlag){
//
//        double basicLongitude;
//        double basicLatitude;
//        int stillIndex = 0;
//
//
//        basicLongitude = GPSInspectors.get(GPSInspectorFlag).getLongitude();
//        basicLatitude = GPSInspectors.get(GPSInspectorFlag).getLatitude();
//
//        for(int i = 1; i < GPSInspectors.size();i++){
//            if((Math.abs(GPSInspectors.get(i).getLongitude() - basicLongitude) > projectLongitude) || (Math.abs(GPSInspectors.get(i).getLatitude() - basicLatitude) > progectLatitude))
//            {
//                if((Math.abs(GPSInspectors.get(i).getLongitude() - basicLongitude) < longitudeInterval) && (Math.abs(GPSInspectors.get(i).getLatitude() - basicLatitude) < latitudeInterval))
//                {
//                    stillIndex += 1;
//                }
//            }
//        }
//        if(stillIndex >= GPSWarning)
//        {
//            IIDao iiDao = new IIDao();
//
//            try{
//                iiDao.add(new InspectorInformation(GPSInspectors.get(GPSInspectorsCapacity / 2 - 1)));
//            }
//            catch(Exception e)
//            {System.out.println("error occurred in GPSInspect"+e);}
//        }
//    }


private  void  GPSInspect(LinkedList<GPSInspector> GPSInspectors ){

    double basicLongitude;
    double basicLatitude;
    int stillIndex = 0;


    basicLongitude = GPSInspectors.getFirst().getLongitude();
    basicLatitude = GPSInspectors.getFirst().getLatitude();

    for(int i = 0; i < GPSInspectors.size();i++){
        if((Math.abs(GPSInspectors.get(i).getLongitude() - basicLongitude) > projectLongitude) || (Math.abs(GPSInspectors.get(i).getLatitude() - basicLatitude) > progectLatitude))
        {
            if((Math.abs(GPSInspectors.get(i).getLongitude() - basicLongitude) < longitudeInterval) && (Math.abs(GPSInspectors.get(i).getLatitude() - basicLatitude) < latitudeInterval))
            {
                stillIndex += 1;
            }
        }
    }
    if(stillIndex >= GPSWarning)
    {
        IIDao iiDao = new IIDao();

        try{
            iiDao.add(new InspectorInformation(GPSInspectors.getLast()));
        }
        catch(Exception e)
        {System.out.println("error occurred in GPSInspect"+e);}
    }
}

//    public  void updateFuelInspectors(FuelInspector fuelInspector){
//
//        if(fuelInspectors.size()<= FuelInspectorsCapacity)
//        {
//            if(fuelInspectors.contains(fuelInspector)||new Date(fuelInspector.getDate().getTime()).before(new Date(DaoUtil.strToDate("2018-1-0").getTime()))){
//                // System.out.println("lueluelue");
//            }else{
//                fuelInspectors.add(fuelInspector);
//            }
//
//
//        }else {
//
//            if(fuelInspectors.contains(fuelInspector)||new Date(fuelInspector.getDate().getTime()).before(new Date(DaoUtil.strToDate("2018-1-0").getTime()))){
//
//                //System.out.println("lueluelue");
//            }else{
//                fuelInspectors.set(fuelInspectorFlag,fuelInspector);
//                fuelInspect(fuelInspectors,fuelInspectorFlag);
//                fuelInspectorFlag++;
//                if(fuelInspectorFlag == FuelInspectorsCapacity){
//                    fuelInspectorFlag = 0;
//                }
//
//
//            }
//        }
//    }
public  void updateFuelInspectors(FuelInspector fuelInspector){
    boolean processed = false;
    if(fuelInspectors.size()< FuelInspectorsCapacity)
    {
        fuelInspectors.offer(fuelInspector);
        if(fuelInspectorFlag < FuelInspectorsCapacity){
            fuelInspectorFlag += 1;
        }

    }else {

        fuelInspectors.poll();
        fuelInspectors.offer(fuelInspector);

        if(fuelInspectorFlag == FuelInspectorsCapacity){
            fuelInspect(fuelInspectors);
            fuelInspectorFlag = 0;
            processed = true;
        }
        if(!processed){
            if(fuelInspectorFlag < FuelInspectorsCapacity){
                fuelInspectorFlag += 1;
            }
        }
    }
}

//    private  void  fuelInspect(List<FuelInspector> fuelInspectors , int fuelInspectorFlag){
//
//        double basicSpeed;
//        double minFuel;
//        double initFuel;
//        int stillIndex = 0;
//
//
//
//        basicSpeed = fuelInspectors.get(fuelInspectorFlag).getSpeed();
//        initFuel = minFuel = fuelInspectors.get(fuelInspectorFlag).getFuel();
//
//        for(int i = 1; i < fuelInspectors.size();i++){
//            if(Math.abs( fuelInspectors.get(i).getSpeed() - basicSpeed ) < speedLimit)
//            {
//                stillIndex += 1;
//            }
//            if(fuelInspectors.get(i).getFuel() < minFuel)
//            {
//                minFuel = fuelInspectors.get(i).getFuel();
//            }
//        }
//        if((stillIndex >= fuelWarning) && (initFuel - minFuel) > fuelInterval)
//        {
//            IIDao iiDao = new IIDao();
//
//            try{
//                iiDao.add(new InspectorInformation(fuelInspectors.get(FuelInspectorsCapacity / 2 - 1)));
//            }
//            catch(Exception e)
//            {System.out.println("error occurred in fuelInspect"+e);}
//        }
//    }
private  void  fuelInspect(LinkedList<FuelInspector> fuelInspectors){

    double basicSpeed;
    double minFuel;
    double initFuel;
    int stillIndex = 0;



    basicSpeed = fuelInspectors.getFirst().getSpeed();
    initFuel = minFuel = fuelInspectors.getFirst().getFuel();

    for(int i = 0; i < fuelInspectors.size();i++){
        if(Math.abs( fuelInspectors.get(i).getSpeed() - basicSpeed ) < speedLimit)
        {
            stillIndex += 1;
        }
        if(fuelInspectors.get(i).getFuel() < minFuel)
        {
            minFuel = fuelInspectors.get(i).getFuel();
        }
    }
    if((stillIndex >= fuelWarning) && (initFuel - minFuel) > fuelInterval)
    {
        IIDao iiDao = new IIDao();

        try{
            iiDao.add(new InspectorInformation(fuelInspectors.getLast()));
        }
        catch(Exception e)
        {System.out.println("error occurred in fuelInspect"+e);}
    }
}
//    public  void updateRotationInspectors(RotationInspector rotationInspector){
//
//        if(rotationInspectors.size()<= rotationInspectorsCapacity)
//        {
//            if(rotationInspectors.contains(rotationInspector)||new Date(rotationInspector.getDate().getTime()).before(new Date(DaoUtil.strToDate("2018-1-0").getTime()))){
//                // System.out.println("lueluelue");
//            }else{
//                rotationInspectors.add(rotationInspector);
//            }
//
//
//        }else {
//
//            if(rotationInspectors.contains(rotationInspector)||new Date(rotationInspector.getDate().getTime()).before(new Date(DaoUtil.strToDate("2018-1-0").getTime()))){
//
//                //System.out.println("lueluelue");
//            }else{
//                rotationInspectors.set(rotationInspectorFlag,rotationInspector);
//                rotationInspect(rotationInspectors,rotationInspectorFlag);
//                rotationInspectorFlag++;
//                if(rotationInspectorFlag == rotationInspectorsCapacity){
//                    rotationInspectorFlag = 0;
//                }
//
//
//            }
//        }
//    }
public  void updateRotationInspectors(RotationInspector rotationInspector){
    boolean processed = false;
    if(rotationInspectors.size()< rotationInspectorsCapacity)
    {
       rotationInspectors.offer(rotationInspector);
        if(rotationInspectorFlag < rotationInspectorsCapacity){
            rotationInspectorFlag += 1;
        }

    }else {

        rotationInspectors.poll();
        rotationInspectors.offer(rotationInspector);

        if(rotationInspectorFlag == rotationInspectorsCapacity){
            rotationInspect(rotationInspectors);
            rotationInspectorFlag = 0;
            processed = true;
        }
        if(!processed){
            if(rotationInspectorFlag < rotationInspectorsCapacity){
                rotationInspectorFlag += 1;
            }
        }
    }
}
//    private void  rotationInspect(List<RotationInspector> rotationInspectors,int rotationInspectorFlag){
//
//        double basicLongitude;
//        double basicLatitude;
//        int stillIndex = 0;
//        boolean initRotation;
//        int rotationIndex = 0;
//
//        basicLongitude = rotationInspectors.get(rotationInspectorFlag).getLongitude();
//        basicLatitude = rotationInspectors.get(rotationInspectorFlag).getLatitude();
//        initRotation = rotationInspectors.get(rotationInspectorFlag).isRotationDirection();
//
//        for(int i = 1; i < rotationInspectors.size();i++){
//            if((Math.abs(rotationInspectors.get(i).getLongitude() - basicLongitude) > longitudeInterval) && (Math.abs(rotationInspectors.get(i).getLatitude() - basicLatitude) > latitudeInterval))
//            {
//                stillIndex += 1;
//            }
//            if(rotationInspectors.get(i).isRotationDirection() != initRotation)
//            {
//                rotationIndex += 1;
//            }
//        }
//        if(stillIndex >= rotationPositionWarning && rotationIndex >= rotationDirectionWarning)
//        {
//            IIDao iiDao = new IIDao();
//
//            try{
//                iiDao.add(new InspectorInformation(rotationInspectors.get(rotationInspectorsCapacity / 2 - 1)));
//            }
//            catch(Exception e)
//            {System.out.println("error occurred in rotationInspect"+e);}
//        }
//    }
private void  rotationInspect(LinkedList<RotationInspector> rotationInspectors){

    double basicLongitude;
    double basicLatitude;
    int stillIndex = 0;
    boolean initRotation;
    int rotationIndex = 0;

    basicLongitude = rotationInspectors.getFirst().getLongitude();
    basicLatitude = rotationInspectors.getFirst().getLatitude();
    initRotation = rotationInspectors.getFirst().isRotationDirection();

    for(int i = 0; i < rotationInspectors.size();i++){
        if((Math.abs(rotationInspectors.get(i).getLongitude() - basicLongitude) > longitudeInterval) && (Math.abs(rotationInspectors.get(i).getLatitude() - basicLatitude) > latitudeInterval))
        {
            stillIndex += 1;
        }
        if(rotationInspectors.get(i).isRotationDirection() != initRotation)
        {
            rotationIndex += 1;
        }
    }
    if(stillIndex >= rotationPositionWarning && rotationIndex >= rotationDirectionWarning)
    {
        IIDao iiDao = new IIDao();

        try{
            iiDao.add(new InspectorInformation(rotationInspectors.getLast()));
        }
        catch(Exception e)
        {System.out.println("error occurred in rotationInspect"+e);}
    }
}


}






