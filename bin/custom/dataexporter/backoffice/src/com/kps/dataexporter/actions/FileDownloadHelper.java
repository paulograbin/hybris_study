package com.kps.dataexporter.actions;

import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import org.zkoss.zhtml.Filedownload;

import java.io.InputStream;


public class FileDownloadHelper {

    public static void executeMediaDownload(final MediaService mediaService, final ImpExMediaModel media) {
        InputStream mediaStream = mediaService.getStreamFromMedia(media);
        String mime = media.getMime();
        String fileName = media.getCode();

        Filedownload.save(mediaStream, mime, fileName);
    }
}
