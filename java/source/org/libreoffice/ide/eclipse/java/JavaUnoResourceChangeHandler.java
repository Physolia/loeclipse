/*************************************************************************
 *
 * $RCSfile: JavaUnoResourceChangeHandler.java,v $
 *
 * $Revision: 1.3 $
 *
 * last change: $Author: cedricbosdo $ $Date: 2007/11/25 20:32:38 $
 *
 * The Contents of this file are made available subject to the terms of
 * the GNU Lesser General Public License Version 2.1
 *
 * Sun Microsystems Inc., October, 2000
 *
 *
 * GNU Lesser General Public License Version 2.1
 * =============================================
 * Copyright 2000 by Sun Microsystems, Inc.
 * 901 San Antonio Road, Palo Alto, CA 94303, USA
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA 02111-1307 USA
 *
 * The Initial Developer of the Original Code is: Sun Microsystems, Inc..
 *
 * Copyright: 2002 by Sun Microsystems, Inc.
 *
 * All Rights Reserved.
 *
 * Contributor(s): Cedric Bosdonnat
 *
 *
 ************************************************************************/
package org.libreoffice.ide.eclipse.java;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IStartup;
import org.libreoffice.ide.eclipse.core.PluginLogger;

/**
 * This class is responsible for reacting to the changes on the Java resources
 * in UNO projects. Its main activity is to maintain the implementation classes
 * list for the services registration.
 *
 * @author cedricbosdo
 *
 */
public class JavaUnoResourceChangeHandler implements IStartup, IResourceChangeListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void earlyStartup() {
        // Start listening the java resources changes
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
        PluginLogger.info("Java UNO resources changes are now listened"); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resourceChanged(IResourceChangeEvent pEvent) {
        try {
            pEvent.getDelta().accept(new JavaResourceDeltaVisitor());
        } catch (Exception e) {
            // Do nothing
        }
    }
}
