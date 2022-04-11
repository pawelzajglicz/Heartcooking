import { trigger, transition, style, animate, state } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { NONE_TYPE } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-under-construction',
  templateUrl: './under-construction.component.html',
  styleUrls: ['./under-construction.component.scss'],
  animations: [
        trigger('hideButton', [
          state('hide', style({
            height: 0,
            'border-style': 'unset',
            'font-size': 0,
            'padding-top': 0,
            'padding-bottom': 0
          }))
        ]),
        trigger('heightHide', [
            state('hide', style({
                height: 0,
                'padding-top': 0,
                'padding-bottom': 0
            })),
            transition('* => *', animate(750))
        ])
    ]
})
export class UnderConstructionComponent implements OnInit {

  state = 'visible';

  changeVisibility(): void {
    (this.state === 'hide') ? this.state = 'visible' : this.state = 'hide';
  }

  constructor() { }

  ngOnInit() {
  }

}
