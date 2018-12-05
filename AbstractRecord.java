package sample;

import javafx.collections.ObservableList;
import javafx.scene.Parent;

import java.io.*;


public  abstract class AbstractRecord implements Serializable{
    private static Parent transformFromRecord(AbstractRecord abstractRecord,double x,double y){

        ///A REMODIFIER CE N'EST PAS ENCORE FINI
       // Parent parent;
        if(abstractRecord instanceof sample.TransitionRecord){
            ObservableList<sample.ShapeTransition> list;
            sample.TransitionRecord transitionRecord =(sample.TransitionRecord)abstractRecord;
            list = sample.TransitionRecord.transformFromRecord(transitionRecord);
            return new sample.TransitionAnimation(list,x,y);

        }else{
            sample.PathRecord pathRecord = (sample.PathRecord)abstractRecord;
            ObservableList<sample.PathShape> list = sample.PathRecord.transfomFromRecord(pathRecord);
            sample.PathAnimation pathAnimation = new sample.PathAnimation(list,x,y);
            return pathAnimation;
        }


    }

    public static Parent loadAnimation(String path,double x,double y)throws Exception{

        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(path))));
        AbstractRecord abstractRecord =(AbstractRecord)objectInputStream.readObject();

            return transformFromRecord(abstractRecord,x,y);

    }

    private boolean isTransition;
    public abstract boolean isTransition();


}
