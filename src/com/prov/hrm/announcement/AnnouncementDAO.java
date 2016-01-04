package com.prov.hrm.announcement;

import java.util.List;

public interface AnnouncementDAO {

	public List<Announcement> getAllAnnouncement(int announcementId);

	public int addAnnouncement(Announcement announcement);

	public int updateAnnouncement(Announcement announcement);

	public int deleteAnnouncement(int announcementId);

	public Announcement getAnnouncementById(int announcementId);

}
