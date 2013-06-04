//
// --------------------------------------------------------------------------
//  Gurux Ltd
// 
//
//
// Filename:        $HeadURL$
//
// Version:         $Revision$,
//                  $Date$
//                  $Author$
//
// Copyright (c) Gurux Ltd
//
//---------------------------------------------------------------------------
//
//  DESCRIPTION
//
// This file is a part of Gurux Device Framework.
//
// Gurux Device Framework is Open Source software; you can redistribute it
// and/or modify it under the terms of the GNU General Public License 
// as published by the Free Software Foundation; version 2 of the License.
// Gurux Device Framework is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of 
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
// See the GNU General Public License for more details.
//
// More information of Gurux products: http://www.gurux.org
//
// This code is licensed under the GNU General Public License v2. 
// Full text may be retrieved at http://www.gnu.org/licenses/gpl-2.0.txt
//---------------------------------------------------------------------------

package gurux.dlms;

/** 
 DLMS specific exception class that has error description available from GetDescription method.
*/
public class GXDLMSException extends RuntimeException
{
    private AssociationResult privateResult = AssociationResult.ACCEPTED;
    private SourceDiagnostic privateDiagnostic = SourceDiagnostic.NONE;
    private int privateErrorCode;

    private static String getDescription(int errCode)
    {
        String str;
        switch (errCode)
        {
            case 1:
                str = "Access Error : Device reports a hardware fault";
                break;
            case 2:
                str = "Access Error : Device reports a temporary failure";
                break;
            case 3:
                str = "Access Error : Device reports Read-Write denied";
                break;
            case 4:
                str = "Access Error : Device reports a undefined object";
                break;
            case 5: 
                str = "Access Error : Device reports a inconsistent Class or object";
                break;
            case 6: 
                str = "Access Error : Device reports a unavailable object";
                break;
            case 7: 
                str = "Access Error : Device reports a unmatched type";
                break;
            case 8:
                str = "Access Error : Device reports scope of access violated";
                break;
            default:
                str = "Unknown error";
            break;
        }
        return str;
    }

    public GXDLMSException(int errCode)
    {
        super(getDescription(errCode));
        setErrorCode(errCode);
    }

    public GXDLMSException(String message)
    {
        super(message);
    }

    /** 
     Constructor for AARE error.
    */
    GXDLMSException(AssociationResult result, SourceDiagnostic diagnostic)
    {
        super("Connection is " + getResult(result) + "\r\n" + getDiagnostic(diagnostic));
    }

    /** 
     Get resulat as a string.

     @param result
     @return 
    */
    private static String getResult(AssociationResult result)
    {
        if (result == AssociationResult.PERMANENT_REJECTED)
        {
            return "permanently rejected";
        }
        if (result == AssociationResult.TRANSIENT_REJECTED)
        {
            return "transient rejected";
        }
        throw new UnsupportedOperationException();
    }

    /** 
     Get diagnostic as a string.
     @param diagnostic
     @return 
    */
    private static String getDiagnostic(SourceDiagnostic diagnostic)
    {
        if (diagnostic == SourceDiagnostic.NO_REASON_GIVEN)
        {
            return "No reason is given.";
        }
        if (diagnostic == SourceDiagnostic.APPLICATION_CONTEXT_NAME_NOT_SUPPORTED)
        {
            return "The application context name is not supported.";
        }
        if (diagnostic == SourceDiagnostic.AUTHENTICATION_MECHANISM_NAME_NOT_RECOGNISED)
        {
            return "The authentication mechanism name is not recognized.";
        }
        if (diagnostic == SourceDiagnostic.AUTHENTICATION_MECHANISM_NAME_REGUIRED)
        {
            return "Authentication mechanism name is required.";
        }
        if (diagnostic == SourceDiagnostic.AUTHENTICATION_FAILURE)
        {
            return "Authentication failure.";
        }
        if (diagnostic == SourceDiagnostic.AUTHENTICATION_REQUIRED)
        {
            return "Authentication is required.";
        }
        throw new UnsupportedOperationException();
}

    /** 
     Returns occurred error code.
    */
    public final int getErrorCode()
    {
        return privateErrorCode;
    }
    public final void setErrorCode(int value)
    {
        privateErrorCode = value;
    }


    /** 
     Returns occurred Association Result in AARE message.
    */
    final AssociationResult getResult()
    {
        return privateResult;
    }
    final void setResult(AssociationResult value)
    {
        privateResult = value;
    }

    /** 
     Returns Diagnostic code in AARE message.
    */
    final SourceDiagnostic getDiagnostic()
    {
        return privateDiagnostic;
    }
    final void setDiagnostic(SourceDiagnostic value)
    {
        privateDiagnostic = value;
    }
}