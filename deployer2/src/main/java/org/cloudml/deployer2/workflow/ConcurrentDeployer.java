/**
 * This file is part of CloudML [ http://cloudml.org ]
 *
 * Copyright (C) 2012 - SINTEF ICT
 * Contact: Franck Chauvel <franck.chauvel@sintef.no>
 *
 * Module: root
 *
 * CloudML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * CloudML is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with CloudML. If not, see
 * <http://www.gnu.org/licenses/>.
 */
/**
 * Created by Maksym on 05.03.2015.
 */
package org.cloudml.deployer2.workflow;

import org.cloudml.core.*;
import org.cloudml.deployer.CloudMLModelComparator;
import org.cloudml.deployer2.dsl.util.ActivityBuilder;
import org.cloudml.deployer2.dsl.util.ActivityValidator;
import org.cloudml.deployer2.workflow.util.ActivityDiagram;
import org.cloudml.deployer2.workflow.util.ActivityDotCreator;
import org.cloudml.deployer2.workflow.util.Parallel;
import org.cloudml.mrt.Coordinator;

import java.util.logging.Logger;

public class ConcurrentDeployer {

    private static final Logger journal = Logger.getLogger(ConcurrentDeployer.class.getName());
    private Coordinator coordinator;
    private ActivityDiagram diagram = new ActivityDiagram();
    private boolean debugMode = true;

//    private Deployment targetModel;

    public ConcurrentDeployer() {}

    public void deploy(Deployment model){
        try {
            //create deployment plan
            diagram.deploy(model);

            //validate activity diagram (deployment plan)
            ActivityValidator.checkActivity(ActivityBuilder.getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create graph in a dot file, it is save in the resources folder
        // tool to view diagram: http://stamm-wilbrandt.de/GraphvizFiddle/
        new ActivityDotCreator(ActivityBuilder.getActivity());

        // traverse graph (execute deployment plan)
        Parallel parallel = new Parallel(ActivityBuilder.getActivity(), debugMode);
//        System.out.println(ActivityBuilder.getActivity().toString());
//        ParallelBFS bfs = new ParallelBFS(ActivityBuilder.getActivity(), debugMode);
    }

    public void deploy(Deployment model, CloudMLModelComparator diff){
        try {
            //create deployment plan
            diagram.deploy(model, diff);

            //validate activity diagram (deployment plan)
            ActivityValidator.checkActivity(ActivityBuilder.getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create graph in a dot file, it is save in the resources folder
        // tool to view diagram: http://stamm-wilbrandt.de/GraphvizFiddle/
        new ActivityDotCreator(ActivityBuilder.getActivity());

        // traverse graph (execute deployment plan)
        Parallel parallel = new Parallel(ActivityBuilder.getActivity(), debugMode);
    }

    public void setCoordinator(Coordinator coordinator){
        diagram.setCoordinator(coordinator);
    }

    public ExternalComponentInstance getDestination(ComponentInstance component){
        return diagram.getDestination(component);
    }

    public void activeDebug(){
        debugMode = true; // this is for new deployer
        diagram.activeDebug();
    }

    public void stopDebug(){
        debugMode = false; // this is for new deployer
        diagram.stopDebug();
    }

    public void scaleOut(VMInstance vmi,Provider provider){
        diagram.scaleOut(vmi, provider);
    }

    public Deployment scaleOut(ExternalComponentInstance eci,Provider provider){
        return diagram.scaleOut(eci, provider);
    }

    public void scaleOut(VMInstance vmi){
        diagram.scaleOut(vmi);
    }

    public void scaleOut(VMInstance vmi, int nb){
        diagram.scaleOut(vmi, nb);
    }

    public void setCurrentModel(Deployment current){
        diagram.setCurrentModel(current);
    }

    // read model from json file
//    private Deployment getDeployment(String pathToModel) {
//        JsonCodec json = new JsonCodec();
//        InputStream is = null;
//        try {
//            is = new FileInputStream(pathToModel);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return (Deployment) json.load(is);
//    }

////        ConcurrentDeployment sensApp = new ConcurrentDeployment("C:\\Users\\Maksym\\Dropbox\\Documents\\Master thesis papers\\sensappAdmin-v2.json");
//        ConcurrentDeployer deployment = new ConcurrentDeployer( "C:\\Users\\Maksym\\Dropbox\\Documents\\Master thesis papers\\ec2.json");
////        ConcurrentDeployment deployment = new ConcurrentDeployment("C:\\Users\\Maksym\\Dropbox\\Documents\\Master thesis papers\\ec2.json", "C:\\Users\\Maksym\\Dropbox\\Documents\\Master thesis papers\\ec2 - Copy.json");


}
