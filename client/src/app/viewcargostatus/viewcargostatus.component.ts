import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpService } from '../../services/http.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-viewcargostatus',
  templateUrl: './viewcargostatus.component.html',
  styleUrls: ['./viewcargostatus.component.scss']
})
export class ViewcargostatusComponent {
  cargo:any={}
  showError:any;
  errorMessage: any;
  cargoIdMd:any;
  constructor(public router:Router, public httpService:HttpService, private formBuilder: FormBuilder, private authService:AuthService) 
  {

  }

  search()
  {
    debugger;
    if(this.cargoIdMd!=null)
    {
      //this.cargo={};
      this.httpService.getOrderStatus(this.cargoIdMd).subscribe((data: any) => {
        this.cargo=data;
        console.log(this.cargo);
      }, error => {
        // Handles error
        this.showError = true;
        this.errorMessage = "No record found with entered Cargo Id. Please enter correct cargo Id";
        console.error('Login error:', error);
      });;
    }
     
    
  }
}
