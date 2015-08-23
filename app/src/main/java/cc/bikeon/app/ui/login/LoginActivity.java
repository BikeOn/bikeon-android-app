package cc.bikeon.app.ui.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.facebook.UiLifecycleHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.R;
import cc.bikeon.app.account.FacebookLoginStrategy;
import cc.bikeon.app.account.FakeLoginStrategy;
import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.account.LoginStrategy;
import cc.bikeon.app.presenter.LoginPresenter;
import cc.bikeon.app.presenter.factories.LoginPresenterFactory;
import cc.bikeon.app.ui.main.MainActivity;
import cc.bikeon.app.views.LoginView;

public class LoginActivity extends Activity
                implements LoginView {

    @InjectView(R.id.logo)
    ImageView logo;
    @InjectView(R.id.btnFacebookLogin)
    ImageButton btnFacebookLogin;
    private UiLifecycleHelper uiHelper;


    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        presenter = LoginPresenterFactory.createFor(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setEvents();
    }

    /**
     * Setter for Login presenter.
     * @param presenter
     */
    public void setLoginPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    private void setEvents() {
        btnFacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginStrategy loginStrategy = null;

                switch (view.getId()) {
                    case R.id.btnFacebookLogin:
                        loginStrategy = new FacebookLoginStrategy(getActivity());
                        break;
                    case 10: //TODO remove before release
                        loginStrategy = new FakeLoginStrategy();
                        break;
                }

                //TODO remove after tests
                loginStrategy = new FakeLoginStrategy();

                presenter.requestLogin(loginStrategy);
            }
        });
    }

    @Override
    public void showError(int messageErrorResId) {
        new AlertDialog.Builder(this)
                       .setMessage(messageErrorResId)
                       .create()
                       .show();
    }

    @Override
    public void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public Activity getActivity() {
        return this;
    }
}