package org.apache.maven.surefire.report;

/*
 * Copyright 2001-2006 The Apache Software Foundation.
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

public class ForkingConsoleReporter
    extends ConsoleReporter
{
    public static final String FORKING_PREFIX_STANDARD = "@SL";

    public static final String FORKING_PREFIX_HEADING = "@HL";

    public static final String FORKING_PREFIX_FOOTER = "@FL";

    public void writeHeading( String message )
    {
        writer.print( FORKING_PREFIX_HEADING );

        super.writeHeading( message );
    }

    public void writeFooter( String footer )
    {
        writer.print( FORKING_PREFIX_FOOTER );

        // Deliberately set to writeMessage
        super.writeMessage( footer );
    }

    public void writeMessage( String message )
    {
        writer.print( FORKING_PREFIX_STANDARD );

        super.writeMessage( message );
    }
}