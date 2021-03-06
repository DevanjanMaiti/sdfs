package org.opendedup.sdfs.mgmt;

import java.io.IOException;

import org.opendedup.logging.SDFSLogger;
import org.opendedup.sdfs.filestore.cloud.FileReplicationService;
import org.opendedup.sdfs.filestore.cloud.RemoteVolumeInfo;
import org.opendedup.sdfs.servers.HCServiceProxy;

public class SyncFromConnectedVolume {

	public void getResult(long id) throws IOException {
		try {
			RemoteVolumeInfo[] l = FileReplicationService.getConnectedVolumes();
			for (RemoteVolumeInfo lv : l) {
				if (lv.id == id) {
					HCServiceProxy.syncVolume(id,true);
					return;
				}
			}
			throw new Exception("volume [" + id + "] not found");
		} catch (Exception e) {
			SDFSLogger.getLog().error("unable to fulfill request", e);
			throw new IOException("request to fetch attributes failed because "
					+ e.toString());
		}
	}

}
