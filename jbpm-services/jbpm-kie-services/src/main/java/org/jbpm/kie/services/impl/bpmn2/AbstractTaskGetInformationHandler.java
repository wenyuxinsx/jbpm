/*
 * Copyright 2011 JBoss Inc 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.kie.services.impl.bpmn2;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.drools.core.xml.ExtensibleXmlParser;
import org.jbpm.bpmn2.xml.TaskHandler;


import org.w3c.dom.Element;
import org.xml.sax.SAXException;

@ApplicationScoped
public class AbstractTaskGetInformationHandler extends TaskHandler {

    private ProcessDescRepoHelper repositoryHelper;
    
    @Inject
    private ProcessDescriptionRepository repository;

    
    public AbstractTaskGetInformationHandler() {
    }

   
    @Override
    protected void handleNode(final org.jbpm.workflow.core.Node node, final Element element, final String uri, 
            final String localName, final ExtensibleXmlParser parser) throws SAXException {
            super.handleNode(node, element, uri, localName, parser);
        String name = super.getTaskName(element);
        String mainProcessId = repositoryHelper.getProcess().getId();
        repository.getProcessDesc(mainProcessId).getServiceTasks().put(node.getName(), name);
       
    }
    
   

    
    public void setRepositoryHelper(ProcessDescRepoHelper repositoryHelper) {
        this.repositoryHelper = repositoryHelper;
    }

    public void setRepository(ProcessDescriptionRepository repository) {
        this.repository = repository;
    }
    
    
}
