import { Component,OnInit } from '@angular/core';
import { TokenService } from '../Services/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  tokendata:any;
     ngOnInit(): void {
      this.tokendata=this.token.getToken();
       
     }
  constructor(private token:TokenService,
    private route:Router){
    
    //  const tokendata= this.token.getToken;
    }
    logOut(){
    this.token.clearToken();
    this.route.navigateByUrl("");
  }

  
}
