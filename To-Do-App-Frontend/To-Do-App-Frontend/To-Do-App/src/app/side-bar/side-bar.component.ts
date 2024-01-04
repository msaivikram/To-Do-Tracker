import { Component, inject } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent {
   
  private breakpointObserver = inject(BreakpointObserver);

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
//    toggleTheme() {
//   const root = document.documentElement;
//   if (root.style.getPropertyValue('--background-color') === 'white') {
//     root.style.setProperty('--background-color', 'black');
//   } else {
//     root.style.setProperty('--background-color', 'white'); 
//   }
// }


toggleTheme() {
  const root = document.documentElement;
  if (root.style.getPropertyValue('--background-color') === 'white') {
    root.style.setProperty('--background-color', 'black');
  } else {
    root.style.setProperty('--background-color', 'white'); 
  }
}

}
