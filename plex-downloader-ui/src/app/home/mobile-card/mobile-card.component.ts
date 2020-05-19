import {Component, Input, OnInit} from '@angular/core';
import {Video} from '../../models/video.model';
import {Directory} from '../../models/directory.model';
import {Router} from '@angular/router';
import {AlertifyService} from '../../_service/alertify.service';
import {LibraryService} from '../../_service/library.service';
import {SeriesPanelComponent} from '../../search/series-panel/series-panel.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-mobile-card',
  templateUrl: './mobile-card.component.html',
  styleUrls: ['./mobile-card.component.css']
})
export class MobileCardComponent implements OnInit {

  constructor(private router: Router,
              private alertify: AlertifyService,
              private libraryService: LibraryService,
              public dialog: MatDialog) { }

  static tempId = 0;

  @Input() video: Video;
  @Input() show: Directory;

  seasons = new Array<Directory>();
  id = 0;

  ngOnInit() {
    this.id = ++MobileCardComponent.tempId;
  }

  startDownloadingMedia(video: Video) {

    this.libraryService.retrieveMediaDownloadLink(video).subscribe(downloadLink => {
      this.beginDownload(downloadLink);
    });

  }

  beginDownload(url: string) {
    let link = document.createElement('a');
    link.download = 'a';
    link.href = url;
    document.body.appendChild(link);
    link.click();
    // window.open(url);
  }

  openShowDialog(show: Directory): void {
    console.log('show example: ' + show.title);
    const dialogRef = this.dialog.open(SeriesPanelComponent, {
      width: '60%',
      data: {show}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  loadSeriesSeasons() {

    this.libraryService.retrieveMediaMetaDataChildren(this.show.key).subscribe((mediaContainer) => {

      this.seasons = mediaContainer.directory;

      this.seasons.forEach(season => {
        console.log('getting season no.' + season.title);
        this.libraryService.retrieveMediaMetaData(season.key).subscribe((media) => {
          season.videos = media.video;
          season.track = media.track;
        });
      });

    });
  }

}