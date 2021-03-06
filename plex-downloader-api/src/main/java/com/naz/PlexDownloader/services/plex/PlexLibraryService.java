package com.naz.PlexDownloader.services.plex;

import com.naz.PlexDownloader.models.plex.Directory;
import com.naz.PlexDownloader.models.plex.MediaContainer;
import com.naz.PlexDownloader.models.plex.Video;

import java.util.List;

public interface PlexLibraryService {

    MediaContainer findPlexResources(String plexAuthToken);

    List<Video> retrieveLibraryOnDeck(String plexAuthToken, String serverIp);

    List<Video> retrieveLibraryRecentlyAdded(String plexAuthToken, String serverIp);

    List<Directory> retrieveLibrarySections(String plexAuthToken, String serverIp);

    List<Directory> retrieveLibrarySectionBySectionKey(String plexAuthToken, String serverIp, String librarySectionKey);

    MediaContainer retrieveLibrarySectionBySectionKeyAndDirectoryKey(String plexAuthToken, String serverIp, String librarySectionKey, String directoryKey);

    List<Video> retrieveMediaMetadata(String plexAuthToken, String serverIp, String libraryKey);

    List<Directory> retrieveMediaMetadataChildren(String plexAuthToken, String serverIp, String libraryKey);

    String retrieveMediaDownloadLink(String plexAuthToken, String serverIp, Video video);

    String retrievePhotoFromPlexServer(String plexAuthToken, String serverIp, String metadataKey);

    MediaContainer retrieveSearchResults(String serverIp, String searchQuery, String authToken);
}
