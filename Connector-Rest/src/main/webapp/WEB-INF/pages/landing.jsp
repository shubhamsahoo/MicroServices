<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta name="description" content="" />
      <meta name="keywords" content="" />
      <title>Wink Friend</title>
      <link rel="icon" href="images/fav.png" type="image/png" sizes="16x16">
      <link rel="stylesheet" href="css/main.min.css">
      <link rel="stylesheet" href="css/style.css">
      <link rel="stylesheet" href="css/color.css">
      <link rel="stylesheet" href="css/responsive.css">
   </head>
   <body>
      <!--<div class="se-pre-con"></div>-->
      <div class="theme-layout">
         <div class="container-fluid pdng0">
            <div class="row merged">
               <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                  <div class="land-featurearea">
                     <div class="land-meta">
                        <% %><h1>winkFriend</h1>
                        <p>Wink Friend is simple and it use to connecting people with friends and family.
                        </p>
                        <div class="friend-logo">
                           <span><img src="images/social-media-communication-world-map.jpg" alt=""></span>
                        </div>
                        <%--<a href="#" title="" class="folow-me">Follow Us on</a>--%>
                     </div>
                  </div>
               </div>
               <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                  <div class="login-reg-bg">
                     <div class="log-reg-area sign">
                        <h2 class="log-title">Login</h2>
                        <p>
                           Login to application and connected with your friends and family.
                        </p>
                        <form method="get" action="/login">
                           <div class="form-group">
                              <input type="text" id="input" required="required" name="userName" /> <label
                                 class="control-label" for="input">Username</label><em
                                 class="mtrl-select"></em>
                           </div>
                           <div class="form-group">
                              <input id="pass" type="password" required="required" name="password" /> <label
                                 class="control-label" for="input">Password</label><em
                                 class="mtrl-select"></em>
                           </div>
                           <div class="checkbox">
                              <label> <input id="show-password" type="checkbox"/><em
                                 class="check-box"></em>Show Password
                              </label>
                           </div>
                           <div class="checkbox">
                              <label> <input type="checkbox" checked="checked" /><em
                                 class="check-box"></em>Always Remember Me.
                              </label>
                           </div>
                           <a href="#" title="" class="forgot-pwd">Forgot Password?</a>
                           <div class="submit-btns">
                              <button class="mtr-btn signin" type="submit">
                              <span>Login</span>
                              </button>
                              <button class="mtr-btn signup" type="button">
                              <span>Register</span>
                              </button>
                           </div>
                        </form>
                     </div>
                     <div class="log-reg-area reg">
                        <h2 class="log-title">Register</h2>
                        <p>
                           It's quick and easy to register with winkFriend.
                        </p>
                        <form method="get" action="/register" name="registration" id="regForm">
                           <div class="form-group">
                              <input type="text" required="required" name="firstName" /> <label
                                 class="control-label" for="input">First Name</label><em id="firstName"
                                 class="mtrl-select"></em>
                           </div>
                           <div class="form-group">
                              <input type="text" required="required" name="lastName" /> <label
                                 class="control-label" for="input">Last Name</label><em id="lastName"
                                 class="mtrl-select"></em>
                           </div>
                           <div class="form-group">
                              <input type="date" required="required" name="dob"/> <label
                                 class="control-label" for="input">Date of Birth</label><em id="dob"
                                 class="mtrl-select"></em>
                           </div>
                           <div class="form-group">
                              <input type="text" required="required" name="mobileNo" pattern="[0-9]{10}"/> <label
                                 class="control-label" for="input">Mobile No</label><em id="mobNo"
                                 class="mtrl-select"></em>
                           </div>
                           <div class="form-group">
                              <input type="text" required="required" name="email" /> <label
                                 class="control-label" for="input"><a
                                 href="/cdn-cgi/l/email-protection" class="__cf_email__"
                                 data-cfemail="eeab838f8782ae">[email&#160;protected]</a></label><em id="email"
                                 class="mtrl-select"></em>
                           </div>
                           <div class="form-group">
                              <input id="reg_pass" type="password" required="required" name="password"/> <label
                                 class="control-label" for="input">Password</label><em id="password"
                                 class="mtrl-select"></em>
                           </div>
                           <div class="form-group">
                              <input id="cfm_pass" type="password" required="required" name="confirmPassword"/> <label
                                id="cfmPassword" class="control-label" for="input">Confirm Password</label><em
                                 class="mtrl-select"></em>
                           </div>
                           <div class="form-radio">
                              <div class="radio">
                                 <label> <input type="radio" name="gender" value="male"
                                    checked="checked" /><em class="check-box"></em>Male
                                 </label>
                              </div>
                              <div class="radio">
                                 <label> <input type="radio" name="gender" value="female"/><em
                                    class="check-box"></em>Female
                                 </label>
                              </div>
                           </div>
                           <div class="checkbox">
                              <label> <input type="checkbox" checked="checked" required="required" name="t&c"/><em
                                 class="check-box"></em><a>Accept Terms &amp; Conditions</a>
                              </label>
                           </div>
                           <a href="#" title="" class="already-have">Already have an
                           account</a>
                           <div class="submit-btns" style="margin-top: -13px;">
                              <button class="mtr-btn signin" type="submit">
                              <span>Register</span>
                              </button>
                              <button class="mtr-btn signin" type="reset">
                              <span>Reset Form</span>
                              </button>
                           </div>
                        </form>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <script data-cfasync="false" src="js/email-decode.min.js"></script>
      <script src="js/main.min.js"></script>
      <script src="js/script.js"></script>
   </body>
</html>