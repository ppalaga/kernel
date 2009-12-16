/*
 * Copyright (C) 2003-2009 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.container.web;

import org.exoplatform.container.RootContainer;
import org.exoplatform.container.util.EnvSpecific;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * This class is used to create and initialize all the portal containers that have been
 * registered previously
 * 
 * Created by The eXo Platform SAS
 * Author : Nicolas Filotto
 *          nicolas.filotto@exoplatform.com
 * 9 sept. 2009  
 */
public class PortalContainerCreator implements ServletContextListener
{

   /**
    * {@inheritDoc}
    */
   public void contextDestroyed(ServletContextEvent event)
   {
   }

   /**
    * Initializes and creates all the portal container that have been registered previously
    */
   public void contextInitialized(ServletContextEvent event)
   {
      ServletContext ctx = event.getServletContext();
      try
      {
         EnvSpecific.initThreadEnv(ctx);
         RootContainer rootContainer = RootContainer.getInstance();
         rootContainer.createPortalContainers();
      }
      finally
      {
         EnvSpecific.cleanupThreadEnv(ctx);
      }
   }
}