
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RefreshService {

    
  private refreshSubject = new BehaviorSubject<boolean>(false);

  setRefresh() {
    this.refreshSubject.next(true);
  }

  getRefresh() {
    return this.refreshSubject.asObservable();
  }
}
