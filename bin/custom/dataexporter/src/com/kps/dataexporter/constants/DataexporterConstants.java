/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved
 */
package com.kps.dataexporter.constants;

/**
 * Global class for all Ybackoffice constants. You can add global constants for your extension into this class.
 */
public final class DataexporterConstants extends GeneratedDataexporterConstants
{
	public static final String EXTENSIONNAME = "dataexporter";

	private DataexporterConstants()
	{
		//empty to avoid instantiating this constant class
	}

	public static final String IMPEX_EXPORT_FILTER_CLAUSE_USING_TYPE =
			"\"#% impex.exportItemsFlexibleSearch( \"\"select {pk} from {%1!} \"\" );\"";
	public static final String IMPEX_EXPORT_FILTER_CLAUSE_USING_TYPE_AND_CATALOG_VERSION =
			"\"#% impex.exportItemsFlexibleSearch( \"\"select {pk} from {%1!} where {catalogVersion} = %2 \"\" );\"";
	public static final String IMPEX_EXPORT_FILTER_CLAUSE_USING_TYPE_AND_PK_LIST =
			"\"#% impex.exportItemsFlexibleSearch( \"\"select {pk} from {%1!} where {pk} in ('%2') \"\" );\"";

}
