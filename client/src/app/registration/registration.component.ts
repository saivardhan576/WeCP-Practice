import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpService } from '../../services/http.service';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent {

  itemForm: FormGroup;
  formModel:any={role:null,email:'',password:'',username:''};
  showMessage:boolean=false;

  responseMessage: any;
  constructor(public router:Router, private httpService:HttpService, private formBuilder: FormBuilder) { 
    
    this.itemForm = this.formBuilder.group({
      username:[this.formModel.username,[Validators.required,Validators.pattern("^[A-Za-z]\\w{5,19}$")]],
      email:[this.formModel.email,[Validators.required,Validators.email]],
      password:[this.formModel.password,[Validators.required,Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}$")]],
      role:[this.formModel.role,[Validators.required]]
    //complete this function 
     
  });
}
  ngOnInit(): void {
  }
  onRegister()
  {
    if(this.itemForm.valid)
    {
      this.showMessage=false;
      this.httpService.registerUser(this.itemForm.value).subscribe(data=>{    
        debugger;
        this.showMessage=true;
        this.responseMessage='Welcome '+data.name+", you are successfully registered";
        this.itemForm.reset();
        
      },error=>{ })
    }
    else{
      this.itemForm.markAllAsTouched();
    }
  //complete this function 
  }


}
