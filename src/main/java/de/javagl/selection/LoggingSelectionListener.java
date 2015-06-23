/*
 * www.javagl.de - Selection - A generic selection model
 *
 * Copyright (c) 2013-2015 Marco Hutter - http://www.javagl.de
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package de.javagl.selection;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A {@link SelectionListener} that prints logging information about
 * the selection changes
 *
 * @param <T> The type of the selected elements
 */
public final class LoggingSelectionListener<T> implements SelectionListener<T>
{
    /**
     * The logger used in this class
     */
    private static final Logger logger = 
        Logger.getLogger(LoggingSelectionListener.class.getName());
    
    /**
     * The log level that will be used for the output
     */
    private Level level = Level.INFO;
    
    /**
     * Create a new listener with a default log level
     */
    public LoggingSelectionListener()
    {
        this(Level.INFO);
    }
    
    /**
     * Create a new listener with the given log level
     * 
     * @param level The level
     */
    public LoggingSelectionListener(Level level)
    {
        this.level = level;
    }

    /**
     * Set the log level that should be used for the output
     * 
     * @param level The level
     */
    public void setLogLevel(Level level)
    {
        this.level = level;
    }

    @Override
    public void selectionChanged(SelectionEvent<T> selectionEvent)
    {
        if (logger.isLoggable(level))
        {
            int threshold = 10;
            Set<T> added = selectionEvent.getAddedElements();
            Set<T> removed = selectionEvent.getRemovedElements();
            Set<T> selected = selectionEvent.getSelectionModel().getSelection();
            logger.log(level, "selectionChanged");
            logger.log(level, "    added   : "+added.size()+" elements: "+
                (added.size() > threshold ? "(String omitted)" : added));
            logger.log(level, "    removed : "+removed.size()+" elements: "+
                (removed.size() > threshold ? "(String omitted)" : removed));
            logger.log(level, "    selected: "+selected.size()+" elements: "+
                (selected.size() > threshold ? "(String omitted)" : selected));
        }
    }
}
