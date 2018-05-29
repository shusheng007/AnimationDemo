package top.ss007.animationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private TextView tvOpr;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOpr=findViewById(R.id.tv_object);
        findViewById(R.id.btn1).setOnClickListener(clickListener);
        findViewById(R.id.btn2).setOnClickListener(clickListener);
        findViewById(R.id.btn3).setOnClickListener(clickListener);
        findViewById(R.id.btn4).setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.btn1:
                    ObjectAnimator tAni= ObjectAnimator.ofFloat(tvOpr,"translationY",600);
                    tAni.setDuration(2000);
                    tAni.start();
                    break;
                case R.id.btn2:
                    ObjectAnimator rAni= ObjectAnimator.ofFloat(tvOpr,"rotationY",0,360);
                    rAni.setDuration(2000);
                    rAni.start();
                    break;
                case R.id.btn3:
                    AnimatorSet aSet=new AnimatorSet();
                    ObjectAnimator tAni2= ObjectAnimator.ofFloat(tvOpr,"translationY",600);
                    tAni2.setDuration(2000);
                    ObjectAnimator rAni2= ObjectAnimator.ofFloat(tvOpr,"rotationY",0,360);
                    rAni2.setDuration(2000);
                    aSet.playTogether(
                            tAni2,
                            rAni2
                    );
                    aSet.setDuration(2000).start();
                    break;
                case R.id.btn4:
                    changeAlpha();
                    break;
                default:
                    break;
            }
        }
    };

    private void changeAlpha()
    {
        // 步骤1：设置动画属性的变化路径
        ValueAnimator anim = ValueAnimator.ofInt(255,0, 255);
        // ofInt（）作用有两个
        // 1. 创建动画实例
        // 2. 将传入的多个Int参数进行平滑过渡
        // 如果传入了3个Int参数 a,b,c ,则是先从a平滑过渡到b,再从b平滑过渡到C，以此类推
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置，即默认设置了如何从初始值过渡到结束值
        // 步骤2：设置动画的播放各种属性
        anim.setDuration(4000);// 设置动画运行的时长
        anim.setStartDelay(0);// 设置动画延迟播放时间
        anim.setRepeatCount(0);// 设置动画重复播放次数 = 重放次数+1  动画播放次数 = infinite时,动画无限重复
        anim.setRepeatMode(ValueAnimator.REVERSE);//RESTART(默认):正序重放 REVERSE:倒序回放
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                int currentValue = (Integer) animation.getAnimatedValue();//步骤3： 获得改变后的值0到255
                float fraction=animation.getAnimatedFraction();//当前动画进度
                System.out.println(currentValue);// 输出改变后的值
                // 步骤4：将改变后的值赋给对象的属性值
                tvOpr.getBackground().setAlpha(currentValue);
                // 步骤5：刷新视图，即重新绘制，从而实现动画效果
                tvOpr.requestLayout();
            }
        });
        anim.start();

        //ValueAnimator.ofObject()
    }

}
