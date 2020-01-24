package com.naz.PlexDownloader.controllers;


import com.naz.PlexDownloader.models.plex.Directory;
import com.naz.PlexDownloader.models.plex.MediaContainer;
import com.naz.PlexDownloader.models.plex.Video;
import com.naz.PlexDownloader.services.plex.PlexLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class PlexLibraryController {

    @Autowired
    private PlexLibraryService plexLibraryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MediaContainer findPlexResources(@RequestParam String authToken) {
        return this.plexLibraryService.findPlexResources(authToken);
    }

    @GetMapping("/{serverIp}/onDeck")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> retrieveLibraryOnDeck(@PathVariable String serverIp,
                                             @RequestParam String authToken) {
        return this.plexLibraryService.retrieveLibraryOnDeck(authToken, serverIp);
    }

    @GetMapping("/{serverIp}/recentlyAdded")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> retrieveLibraryRecentlyAdded(@PathVariable String serverIp,
                                                    @RequestParam String authToken) {
        return this.plexLibraryService.retrieveLibraryRecentlyAdded(authToken, serverIp);
    }

    @GetMapping("/{serverIp}/sections")
    @ResponseStatus(HttpStatus.OK)
    public List<Directory> retrieveLibrarySections(@PathVariable String serverIp,
                                                   @RequestParam String authToken) {
        return this.plexLibraryService.retrieveLibrarySections(authToken, serverIp);
    }

    @GetMapping("/{serverIp}/sections/{sectionKey}")
    @ResponseStatus(HttpStatus.OK)
    public List<Directory> retrieveLibrarySectionBySectionKey(@PathVariable String serverIp,
                                                              @PathVariable String sectionKey,
                                                              @RequestParam String authToken) {
        return this.plexLibraryService.retrieveLibrarySectionBySectionKey(authToken, serverIp, sectionKey);
    }

    @GetMapping("/{serverIp}/sections/{sectionKey}/directory/{directoryKey}")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> retrieveLibrarySectionBySectionKeyAndDirectoryKey(@PathVariable String serverIp,
                                                                         @PathVariable String sectionKey,
                                                                         @PathVariable String directoryKey,
                                                                         @RequestParam String authToken) {

        //DirectoryKey theDirectoryKey = DirectoryKey.valueOf(directoryKey);

        return this.plexLibraryService.retrieveLibrarySectionBySectionKeyAndDirectoryKey(authToken, serverIp, sectionKey, directoryKey);
    }


    @GetMapping("/{serverIp}/metadata")
    @ResponseStatus(HttpStatus.OK)
    public List<Video> retrieveMediaMetadata(@PathVariable String serverIp,
                                       @RequestParam String authToken,
                                       @RequestParam String libraryKey) {
        return this.plexLibraryService.retrieveMediaMetadata(authToken, serverIp, libraryKey);
    }

    @GetMapping("/{serverIp}/metadata_children")
    @ResponseStatus(HttpStatus.OK)
    public List<Directory> retrieveMediaMetadataChildren(@PathVariable String serverIp,
                                                         @RequestParam String authToken,
                                                         @RequestParam String libraryKey) {
        return this.plexLibraryService.retrieveMediaMetadataChildren(authToken, serverIp, libraryKey);
    }

    @PostMapping("/{serverIp}/metadata")
    @ResponseStatus(HttpStatus.OK)
    public String retrieveMediaDownloadLink(@PathVariable String serverIp,
                                            @RequestParam String authToken,
                                            @RequestBody Video video) {
        return this.plexLibraryService.retrieveMediaDownloadLink(authToken, serverIp, video);
    }

    @GetMapping("/{serverIp}/search/{searchQuery}")
    @ResponseStatus(HttpStatus.OK)
    public MediaContainer retrieveSearchResults(@PathVariable String serverIp,
                                                @PathVariable String searchQuery,
                                                @RequestParam String authToken) {

        return this.plexLibraryService.retrieveSearchResults(serverIp, searchQuery, authToken);
    }

    public PlexLibraryService getPlexLibraryService() {
        return plexLibraryService;
    }

    public void setPlexLibraryService(PlexLibraryService plexLibraryService) {
        this.plexLibraryService = plexLibraryService;
    }
}
