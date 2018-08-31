import { Component, Output, OnInit, EventEmitter, Input } from '@angular/core';
import { ViewChild } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { RestService } from '../rest.service';
import { ProtocolDescription } from '../model/connect/grounding/ProtocolDescription';
import { FormatDescription } from '../model/connect/grounding/FormatDescription';
import { AdapterDescription } from '../model/connect/AdapterDescription';
import { DataSetDescription } from '../model/DataSetDescription';
import { EventSchema } from '../schema-editor/model/EventSchema';
import { MatDialog } from '@angular/material';
import { AdapterStartedDialog } from './component/adapter-started-dialog.component';
import { Logger } from '../../shared/logger/default-log.service';
import { AdapterStreamDescription } from '../model/connect/AdapterStreamDescription';
import { AdapterSetDescription } from '../model/connect/AdapterSetDescription';
import { DataStreamDescription } from '../model/DataStreamDescription';
import { GenericAdapterSetDescription } from '../model/connect/GenericAdapterSetDescription';
import { GenericAdapterStreamDescription } from '../model/connect/GenericAdapterStreamDescription';

@Component({
  selector: 'sp-new-adapter',
  templateUrl: './new.component.html',
  styleUrls: ['./new.component.css'],
})
export class NewComponent implements OnInit {
  @ViewChild('eschema')
  eventSchemaComponent;
  @Output()
  newAdapterCreated = new EventEmitter();
  @Input()
  adapter: AdapterDescription;
  allFormats: FormatDescription[];
  isLinear = true;

  hasInputProtocol: Boolean;
  hasInputFormat: Boolean;
  hasInput: Boolean[];
  inputValue = '';

  constructor(
    private logger: Logger,
    private restService: RestService,
    private _formBuilder: FormBuilder,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    this.allFormats = [];
    this.restService.getFormats().subscribe(x => {
      this.allFormats = x.list;
      this.allFormats;
    });
  }

  public startAdapter() {
    let dialogRef = this.dialog.open(AdapterStartedDialog, {
      // width: '250px',
      // data: { name: this.name, animal: this.animal }
    });

    this.restService.addAdapter(this.adapter);

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.newAdapterCreated.emit();
    });
  }

  formatSelected(selectedFormat) {
    if (
      this.adapter instanceof GenericAdapterSetDescription ||
      this.adapter instanceof GenericAdapterStreamDescription
    ) {
      this.adapter.format = selectedFormat;
    }
  }
}
